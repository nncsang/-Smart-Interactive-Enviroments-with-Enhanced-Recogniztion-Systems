using System.ServiceModel;
using System.ServiceModel.Web;
using System.IO;

namespace RestService
{
    
    [ServiceContract]
    public interface IRestServiceImpl
    {
        //[OperationContract]
        //[WebInvoke(Method = "POST", UriTemplate = "upImage/{id}")]
        //string UploadImage(string id, Stream fileStream);

        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "hello/")]
        string Hello();

        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "init/")]
        string Init();

        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Json,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "detect/{name}")]
        string Detect(string name);

        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Json,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "matching/{name}")]
        string Matching(string name);
    }
}