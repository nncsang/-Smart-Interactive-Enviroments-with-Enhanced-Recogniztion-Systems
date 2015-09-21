using System.IO;
using System;
using System.Runtime.InteropServices;
using System.Collections.Generic;
using System.Web.Script.Serialization;
using System.Linq;

namespace RestService
{
    public class RestServiceImpl : IRestServiceImpl
    {
        #region IRestServiceImpl Members
        [DllImport("Detector.DLL")]
        public static extern int HelloWorld();

        [DllImport("Detector.DLL")]
        public static extern void loadData(string centroids, string model);

        [DllImport("Detector.DLL")]
        public static extern int detectLocation(string input);

        public static string CentroidURL = "D:\\codebook_tree.txt";
        public static string ModelURL = "D:\\model.txt";
        public static LandmarkDBEntities dbLandmark = new LandmarkDBEntities();
        public static BookDBEntities dbBook = new BookDBEntities();

        private static string UploadFolder = @"C:\Users\ChauSang\Desktop\All\Data";
        private static JavaScriptSerializer jsonSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();

        public class InfoMatching
        {
            public String Field1 = null;
            public String Field2 = null;
            public String Field3 = null;
            public String Field4 = null;
            public String Field5 = null;
            public String Field6 = null;
            public String Field7 = null;
            public String Field8 = null;
            public String Field9 = null;
            public String Field10 = null;
            public String Field11 = null;

            public List<String> Photos = null;
            public List<String> Photos_Desc = null;
            public List<String> Videos = null;
            public List<String> Videos_Desc = null;
            public List<String> Title = null;
            public List<String> Body = null;

            public int Type;

            public InfoMatching(String f1, String f2, String f3, String f4, String f5, String f6, String f7, String f8, String f9, String f10, String f11,
                List<String> photos, List<String> videos, List<String> photos_decs, List<String> videos_desc, List<String> title, List<String> body, int type)
            {
                Field1 = f1;
                Field2 = f2;
                Field3 = f3;
                Field4 = f4;
                Field5 = f5;
                Field6 = f6;
                Field7 = f7;
                Field8 = f8;
                Field9 = f9;
                Field10 = f10;
                Field11 = f11;

                Type = type;

                Photos = photos;
                Photos_Desc = photos_decs;
                Videos = videos;
                Videos_Desc = videos_desc;
                Title = title;
                Body = body;
            }
        }

        public class Landmark
        {
            public String Name = null;
            public String Address = null;
            public String Description = null;
            public String Phone = null;
            public List<String> Photos = null;
            public List<String> Photos_Desc = null;
            public List<String> Videos = null;
            public List<String> Videos_Desc = null;
            public String WikiKey = null;
            public String Type = null;
            public String ThumbUrl = null;

            public Landmark(String name, String address, String description, String phone, List<String> photos,
                List<String> videos, List<String> photos_decs, List<String> videos_desc, String wikiKey, String type, String thumbUrl)
            {
                Name = name;
                Address = address;
                Description = description;
                Phone = phone;
                Photos = photos;
                Photos_Desc = photos_decs;
                Videos = videos;
                Videos_Desc = videos_desc;
                WikiKey = wikiKey;
                Type = type;
                ThumbUrl = thumbUrl; ;
            }
        }

        public string Hello()
        {
            try
            {
                HelloWorld().ToString();
            }
            catch (Exception ex)
            {
                return ex.ToString();
            }
            return "OK";
        }

        public string Init()
        {
            try
            {
                loadData(CentroidURL, ModelURL);
            }
            catch (Exception ex)
            {
                return ex.ToString();
            }
            return "OK";
        }

        public string Matching(string name)
        {
            int result = Int32.Parse(name);
            InfoMatching book = null;
            string file = UploadFolder + "\\" + name + ".jpg";

            try
            {

                //result = detectLocation(file);
                var info = from i in dbBook.Infors where i.ID == result select i;
                var photo = from p in dbBook.ImageBooks where p.BookID == result select p;
                var video = from v in dbBook.VideoBooks where v.BookID == result select v;
                var review = from r in dbBook.Reviews where r.IDInfor == result select r;

                foreach (Infor i in info)
                {
                    List<String> photos = new List<String>();
                    List<String> photos_desc = new List<string>();
                    foreach (ImageBook image in photo)
                    {
                        photos.Add(image.URL);
                        photos_desc.Add(image.Name);
                    }

                    List<String> videos = new List<String>();
                    List<String> videos_desc = new List<String>();
                    foreach (VideoBook v in video)
                    {
                        videos.Add(v.URL);
                        videos_desc.Add(v.Name);
                    }

                    List<String> titles = new List<String>();
                    List<String> bodies = new List<String>();

                    foreach (Review r in review)
                    {
                        titles.Add(r.Title);
                        bodies.Add(r.Body);
                    }
                    book = new InfoMatching(i.Field1, i.Field2, i.Field3, i.Field4, i.Field5, i.Field6, i.Field7, i.Field8, i.Field9, i.Field10, i.Field11,
                            photos, videos, photos_desc, videos_desc, titles, bodies, i.Type);
                }
                //File.Delete(UploadFolder + "\\" + name + ".png");
            }
            catch (Exception ex)
            {
                return ex.ToString();
            }
            return jsonSerializer.Serialize(book);
        }


        public string Detect(string name)
        {
            int result = Int32.Parse(name);
            Landmark landmark = null;
            string file = UploadFolder + "\\" + name + ".jpg";

            try
            {
                
                //result = detectLocation(file);
                var info = from i in dbLandmark.Infoes where i.ID == result select i;
                var photo = from p in dbLandmark.Images where p.LandmarkID == result select p;
                var video = from v in dbLandmark.Videos where v.LandmarkID == result select v;

                foreach (Info i in info)
                {
                    List<String> photos = new List<String>();
                    List<String> photos_desc = new List<string>();

                    foreach (Image image in photo)
                    {
                        photos.Add(image.URL);
                        photos_desc.Add(image.Name);
                    }

                    List<String> videos = new List<String>();
                    List<String> videos_desc = new List<String>();

                    foreach (Video v in video)
                    {
                        videos.Add(v.URL);
                        videos_desc.Add(v.Name);
                    }

                    landmark = new Landmark(i.Name, i.Address, i.Shot_Intro, i.Phone,
                        photos, videos, photos_desc, videos_desc, i.WikiKey, i.Type.ToString(), i.Thumbnail_URL);
                }

                //File.Delete(UploadFolder + "\\" + name + ".png");
            }
            catch (Exception ex)
            {
                return ex.ToString();
            }
            return jsonSerializer.Serialize(landmark);
        }

        #endregion
    }
}