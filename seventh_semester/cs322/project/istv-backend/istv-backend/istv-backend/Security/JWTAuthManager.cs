using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using istv_backend.Data.Entity;
using Microsoft.IdentityModel.Tokens;

namespace istv_backend.Security; 

public class JWTAuthManager {
    private readonly JWTConfig _jwt;
    private readonly byte[] _sec;

    public JWTAuthManager(JWTConfig jwtConfig) {
        _jwt = jwtConfig;
        _sec = Encoding.ASCII.GetBytes(jwtConfig.Secret);
    }
    
    public JWTAuthResult GenerateTokens(User user, List<String> roles) {
        var signingCredentials = new SigningCredentials(
            new SymmetricSecurityKey(_sec),
            SecurityAlgorithms.HmacSha256Signature);
        var payload = new JwtPayload {
            {"user", user.Username},
            {"sub", user.Id},
            {"roles", roles},
            {"aud", _jwt.Audience},
            {"iss", _jwt.Issuer},
            {"iat", DateTimeOffset.UtcNow.ToUnixTimeSeconds()},
            {"exp", DateTimeOffset.UtcNow.AddHours(2).ToUnixTimeSeconds()}
        };
        var token =
            new JwtSecurityToken(new JwtHeader(signingCredentials), payload);
        var accessToken = new JwtSecurityTokenHandler().WriteToken(token);

        return new JWTAuthResult() {
            Token = accessToken
        };
    }
    
    public (ClaimsPrincipal, JwtSecurityToken) DecodeJwtToken(string token) {
        if (string.IsNullOrWhiteSpace(token)) {
            throw new SecurityTokenException("Invalid token");
        }

        var principal = new JwtSecurityTokenHandler()
            .ValidateToken(token,
                new TokenValidationParameters {
                    ValidateIssuer = true,
                    ValidIssuer = _jwt.Issuer,
                    ValidateIssuerSigningKey = true,
                    IssuerSigningKey = new SymmetricSecurityKey(_sec),
                    ValidAudience = _jwt.Audience,
                    ValidateAudience = true,
                    ValidateLifetime = true,
                    ClockSkew = TimeSpan.FromMinutes(1)
                },
                out var validatedToken);
        return (principal, validatedToken as JwtSecurityToken)!;
    }
}