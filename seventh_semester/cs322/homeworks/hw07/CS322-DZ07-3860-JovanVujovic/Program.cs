/*
 * Nisu korišćene windows forme s obzirom da nemam windows instaliran na računaru. Tema je stavljena na rad sa bazama
 */

using System;
using System.Collections.Generic;
using MySql.Data.MySqlClient;


namespace CS322_DZ07_3860_JovanVujovic {
    internal class Program {
        private const string CreateTable = @"
            create table if not exists `course` (
                `course_id` int auto_increment,
                `code` varchar(64) not null,
                `name` varchar(64) not null,
                PRIMARY KEY(course_id)
            );
            ";

        public static void Main(string[] args) {
            string dbCon = @"server=localhost;userid=cs;password=cs322;database=faculty";

            MySqlConnection connection = new MySqlConnection(dbCon);
            connection.Open();

            setUpTables(connection);

            Course cs322 = add(new Course( "CS322", "Programiranje u C#"), connection);
            Course cs324 = add(new Course( "CS324", "Skripting jezici"), connection);
            Course se322 = add(new Course( "SE322", "Inzenjerstvo zahteva"), connection);

            cs322.code = "CS3222";
            cs322 = update(cs322, connection);

            List<Course> courses = getAll(connection);
            foreach (Course course in courses) {
                Console.WriteLine(course);
            }
            
            connection.Close();
        }

        private static void setUpTables(MySqlConnection connection) {
            MySqlCommand createCommand = new MySqlCommand(CreateTable, connection);
            createCommand.ExecuteScalar();
        }

        private static Course add(Course course, MySqlConnection connection) {
            MySqlCommand add = new MySqlCommand("insert into `course`(code, name) values (@code, @name);", connection);

            add.Parameters.AddWithValue("@code", course.code);
            add.Parameters.AddWithValue("@name", course.name);
            add.Prepare();

            add.ExecuteNonQuery();
            return course;
        }
        
        private static Course update(Course course, MySqlConnection connection) {
            MySqlCommand add = new MySqlCommand("update `course` set code=@code, name=@name where course_id=@id;", connection);

            add.Parameters.AddWithValue("@id", course.id);
            add.Parameters.AddWithValue("@code", course.code);
            add.Parameters.AddWithValue("@name", course.name);
            add.Prepare();

            add.ExecuteNonQuery();
            return course;
        }

        private static void delete(Course course, MySqlConnection connection) {
            MySqlCommand delete = new MySqlCommand("delete from course where course_id = @id;", connection);

            delete.Parameters.AddWithValue("@id", course.id);
            delete.Prepare();

            delete.ExecuteNonQuery();
        }

        private static List<Course> getAll(MySqlConnection connection) {
            MySqlCommand select = new MySqlCommand("select * from course;", connection);
            MySqlDataReader rdr = select.ExecuteReader();
            List<Course> courses = new List<Course>();

            while (rdr.Read()) {
                int id = rdr.GetInt32(0);
                string code = rdr.GetString(1);
                string name = rdr.GetString(2);
                courses.Add(new Course( code, name));
            }

            return courses;
        }
    }
}