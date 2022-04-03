namespace CS322_DZ07_3860_JovanVujovic
{
    public class Course
    {
        public int id { set; get; }
        public string code { set; get; }
        public string name { set; get; }

        public Course( string code, string name)
        {
            this.code = code;
            this.name = name;
        }

        public override string ToString()
        {
            return $"{code} - {name}";
        }
    }
}