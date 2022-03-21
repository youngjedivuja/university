namespace CS322_DZ11_3860_JovanVujovic; 

public class WriterEx {

    public interface Writer {
        void write(String fileName, String text);
    }

    public abstract class AbstractWriter : Writer {
        protected string type;

        protected AbstractWriter(string type) {
            type = type;
        }

        public abstract void write(string fileName, string text);
    }

    public class FileWriter : AbstractWriter {
        public FileWriter(string type) : base(type) {
        }

        public override void write(string fileName, string text) {
            Console.WriteLine("Writer upisuje " + fileName + " sledeci tekst: " + text);
        }
    }

    public class S3Writer : AbstractWriter {
        private readonly string s3bucketName;

        public S3Writer(string type) : base(type) {
        }

        public override void write(string fileName, string text) {
            Console.WriteLine("U okvirtu bucket-a " + s3bucketName + " writer upisuje sledeci file " + fileName + " sa sledecim tekstom: " + text);
        }
    }
}