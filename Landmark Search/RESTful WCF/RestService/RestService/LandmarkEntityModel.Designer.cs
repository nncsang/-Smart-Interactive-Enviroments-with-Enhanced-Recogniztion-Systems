﻿//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.ComponentModel;
using System.Data.EntityClient;
using System.Data.Objects;
using System.Data.Objects.DataClasses;
using System.Linq;
using System.Runtime.Serialization;
using System.Xml.Serialization;

[assembly: EdmSchemaAttribute()]
#region EDM Relationship Metadata

[assembly: EdmRelationshipAttribute("LandmarkDBModel", "FK_Image_Info", "Info", System.Data.Metadata.Edm.RelationshipMultiplicity.One, typeof(RestService.Info), "Image", System.Data.Metadata.Edm.RelationshipMultiplicity.Many, typeof(RestService.Image), true)]
[assembly: EdmRelationshipAttribute("LandmarkDBModel", "FK_Video_Info", "Info", System.Data.Metadata.Edm.RelationshipMultiplicity.One, typeof(RestService.Info), "Video", System.Data.Metadata.Edm.RelationshipMultiplicity.Many, typeof(RestService.Video), true)]

#endregion

namespace RestService
{
    #region Contexts
    
    /// <summary>
    /// No Metadata Documentation available.
    /// </summary>
    public partial class LandmarkDBEntities : ObjectContext
    {
        #region Constructors
    
        /// <summary>
        /// Initializes a new LandmarkDBEntities object using the connection string found in the 'LandmarkDBEntities' section of the application configuration file.
        /// </summary>
        public LandmarkDBEntities() : base("name=LandmarkDBEntities", "LandmarkDBEntities")
        {
            this.ContextOptions.LazyLoadingEnabled = true;
            OnContextCreated();
        }
    
        /// <summary>
        /// Initialize a new LandmarkDBEntities object.
        /// </summary>
        public LandmarkDBEntities(string connectionString) : base(connectionString, "LandmarkDBEntities")
        {
            this.ContextOptions.LazyLoadingEnabled = true;
            OnContextCreated();
        }
    
        /// <summary>
        /// Initialize a new LandmarkDBEntities object.
        /// </summary>
        public LandmarkDBEntities(EntityConnection connection) : base(connection, "LandmarkDBEntities")
        {
            this.ContextOptions.LazyLoadingEnabled = true;
            OnContextCreated();
        }
    
        #endregion
    
        #region Partial Methods
    
        partial void OnContextCreated();
    
        #endregion
    
        #region ObjectSet Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        public ObjectSet<Image> Images
        {
            get
            {
                if ((_Images == null))
                {
                    _Images = base.CreateObjectSet<Image>("Images");
                }
                return _Images;
            }
        }
        private ObjectSet<Image> _Images;
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        public ObjectSet<Info> Infoes
        {
            get
            {
                if ((_Infoes == null))
                {
                    _Infoes = base.CreateObjectSet<Info>("Infoes");
                }
                return _Infoes;
            }
        }
        private ObjectSet<Info> _Infoes;
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        public ObjectSet<Video> Videos
        {
            get
            {
                if ((_Videos == null))
                {
                    _Videos = base.CreateObjectSet<Video>("Videos");
                }
                return _Videos;
            }
        }
        private ObjectSet<Video> _Videos;

        #endregion

        #region AddTo Methods
    
        /// <summary>
        /// Deprecated Method for adding a new object to the Images EntitySet. Consider using the .Add method of the associated ObjectSet&lt;T&gt; property instead.
        /// </summary>
        public void AddToImages(Image image)
        {
            base.AddObject("Images", image);
        }
    
        /// <summary>
        /// Deprecated Method for adding a new object to the Infoes EntitySet. Consider using the .Add method of the associated ObjectSet&lt;T&gt; property instead.
        /// </summary>
        public void AddToInfoes(Info info)
        {
            base.AddObject("Infoes", info);
        }
    
        /// <summary>
        /// Deprecated Method for adding a new object to the Videos EntitySet. Consider using the .Add method of the associated ObjectSet&lt;T&gt; property instead.
        /// </summary>
        public void AddToVideos(Video video)
        {
            base.AddObject("Videos", video);
        }

        #endregion

    }

    #endregion

    #region Entities
    
    /// <summary>
    /// No Metadata Documentation available.
    /// </summary>
    [EdmEntityTypeAttribute(NamespaceName="LandmarkDBModel", Name="Image")]
    [Serializable()]
    [DataContractAttribute(IsReference=true)]
    public partial class Image : EntityObject
    {
        #region Factory Method
    
        /// <summary>
        /// Create a new Image object.
        /// </summary>
        /// <param name="id">Initial value of the ID property.</param>
        /// <param name="landmarkID">Initial value of the LandmarkID property.</param>
        /// <param name="iD_Dataset">Initial value of the ID_Dataset property.</param>
        public static Image CreateImage(global::System.Int32 id, global::System.Int32 landmarkID, global::System.Int32 iD_Dataset)
        {
            Image image = new Image();
            image.ID = id;
            image.LandmarkID = landmarkID;
            image.ID_Dataset = iD_Dataset;
            return image;
        }

        #endregion

        #region Primitive Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=true, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID
        {
            get
            {
                return _ID;
            }
            set
            {
                if (_ID != value)
                {
                    OnIDChanging(value);
                    ReportPropertyChanging("ID");
                    _ID = StructuralObject.SetValidValue(value);
                    ReportPropertyChanged("ID");
                    OnIDChanged();
                }
            }
        }
        private global::System.Int32 _ID;
        partial void OnIDChanging(global::System.Int32 value);
        partial void OnIDChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 LandmarkID
        {
            get
            {
                return _LandmarkID;
            }
            set
            {
                OnLandmarkIDChanging(value);
                ReportPropertyChanging("LandmarkID");
                _LandmarkID = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("LandmarkID");
                OnLandmarkIDChanged();
            }
        }
        private global::System.Int32 _LandmarkID;
        partial void OnLandmarkIDChanging(global::System.Int32 value);
        partial void OnLandmarkIDChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Name
        {
            get
            {
                return _Name;
            }
            set
            {
                OnNameChanging(value);
                ReportPropertyChanging("Name");
                _Name = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Name");
                OnNameChanged();
            }
        }
        private global::System.String _Name;
        partial void OnNameChanging(global::System.String value);
        partial void OnNameChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String URL
        {
            get
            {
                return _URL;
            }
            set
            {
                OnURLChanging(value);
                ReportPropertyChanging("URL");
                _URL = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("URL");
                OnURLChanged();
            }
        }
        private global::System.String _URL;
        partial void OnURLChanging(global::System.String value);
        partial void OnURLChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID_Dataset
        {
            get
            {
                return _ID_Dataset;
            }
            set
            {
                OnID_DatasetChanging(value);
                ReportPropertyChanging("ID_Dataset");
                _ID_Dataset = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("ID_Dataset");
                OnID_DatasetChanged();
            }
        }
        private global::System.Int32 _ID_Dataset;
        partial void OnID_DatasetChanging(global::System.Int32 value);
        partial void OnID_DatasetChanged();

        #endregion

    
        #region Navigation Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [XmlIgnoreAttribute()]
        [SoapIgnoreAttribute()]
        [DataMemberAttribute()]
        [EdmRelationshipNavigationPropertyAttribute("LandmarkDBModel", "FK_Image_Info", "Info")]
        public Info Info
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Image_Info", "Info").Value;
            }
            set
            {
                ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Image_Info", "Info").Value = value;
            }
        }
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [BrowsableAttribute(false)]
        [DataMemberAttribute()]
        public EntityReference<Info> InfoReference
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Image_Info", "Info");
            }
            set
            {
                if ((value != null))
                {
                    ((IEntityWithRelationships)this).RelationshipManager.InitializeRelatedReference<Info>("LandmarkDBModel.FK_Image_Info", "Info", value);
                }
            }
        }

        #endregion

    }
    
    /// <summary>
    /// No Metadata Documentation available.
    /// </summary>
    [EdmEntityTypeAttribute(NamespaceName="LandmarkDBModel", Name="Info")]
    [Serializable()]
    [DataContractAttribute(IsReference=true)]
    public partial class Info : EntityObject
    {
        #region Factory Method
    
        /// <summary>
        /// Create a new Info object.
        /// </summary>
        /// <param name="id">Initial value of the ID property.</param>
        /// <param name="iD_Dataset">Initial value of the ID_Dataset property.</param>
        public static Info CreateInfo(global::System.Int32 id, global::System.Int32 iD_Dataset)
        {
            Info info = new Info();
            info.ID = id;
            info.ID_Dataset = iD_Dataset;
            return info;
        }

        #endregion

        #region Primitive Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=true, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID
        {
            get
            {
                return _ID;
            }
            set
            {
                if (_ID != value)
                {
                    OnIDChanging(value);
                    ReportPropertyChanging("ID");
                    _ID = StructuralObject.SetValidValue(value);
                    ReportPropertyChanged("ID");
                    OnIDChanged();
                }
            }
        }
        private global::System.Int32 _ID;
        partial void OnIDChanging(global::System.Int32 value);
        partial void OnIDChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Name
        {
            get
            {
                return _Name;
            }
            set
            {
                OnNameChanging(value);
                ReportPropertyChanging("Name");
                _Name = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Name");
                OnNameChanged();
            }
        }
        private global::System.String _Name;
        partial void OnNameChanging(global::System.String value);
        partial void OnNameChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Address
        {
            get
            {
                return _Address;
            }
            set
            {
                OnAddressChanging(value);
                ReportPropertyChanging("Address");
                _Address = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Address");
                OnAddressChanged();
            }
        }
        private global::System.String _Address;
        partial void OnAddressChanging(global::System.String value);
        partial void OnAddressChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public Nullable<global::System.Double> Latitude
        {
            get
            {
                return _Latitude;
            }
            set
            {
                OnLatitudeChanging(value);
                ReportPropertyChanging("Latitude");
                _Latitude = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("Latitude");
                OnLatitudeChanged();
            }
        }
        private Nullable<global::System.Double> _Latitude;
        partial void OnLatitudeChanging(Nullable<global::System.Double> value);
        partial void OnLatitudeChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public Nullable<global::System.Double> Longitude
        {
            get
            {
                return _Longitude;
            }
            set
            {
                OnLongitudeChanging(value);
                ReportPropertyChanging("Longitude");
                _Longitude = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("Longitude");
                OnLongitudeChanged();
            }
        }
        private Nullable<global::System.Double> _Longitude;
        partial void OnLongitudeChanging(Nullable<global::System.Double> value);
        partial void OnLongitudeChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Thumbnail_URL
        {
            get
            {
                return _Thumbnail_URL;
            }
            set
            {
                OnThumbnail_URLChanging(value);
                ReportPropertyChanging("Thumbnail_URL");
                _Thumbnail_URL = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Thumbnail_URL");
                OnThumbnail_URLChanged();
            }
        }
        private global::System.String _Thumbnail_URL;
        partial void OnThumbnail_URLChanging(global::System.String value);
        partial void OnThumbnail_URLChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Shot_Intro
        {
            get
            {
                return _Shot_Intro;
            }
            set
            {
                OnShot_IntroChanging(value);
                ReportPropertyChanging("Shot_Intro");
                _Shot_Intro = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Shot_Intro");
                OnShot_IntroChanged();
            }
        }
        private global::System.String _Shot_Intro;
        partial void OnShot_IntroChanging(global::System.String value);
        partial void OnShot_IntroChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Phone
        {
            get
            {
                return _Phone;
            }
            set
            {
                OnPhoneChanging(value);
                ReportPropertyChanging("Phone");
                _Phone = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Phone");
                OnPhoneChanged();
            }
        }
        private global::System.String _Phone;
        partial void OnPhoneChanging(global::System.String value);
        partial void OnPhoneChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=true, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID_Dataset
        {
            get
            {
                return _ID_Dataset;
            }
            set
            {
                if (_ID_Dataset != value)
                {
                    OnID_DatasetChanging(value);
                    ReportPropertyChanging("ID_Dataset");
                    _ID_Dataset = StructuralObject.SetValidValue(value);
                    ReportPropertyChanged("ID_Dataset");
                    OnID_DatasetChanged();
                }
            }
        }
        private global::System.Int32 _ID_Dataset;
        partial void OnID_DatasetChanging(global::System.Int32 value);
        partial void OnID_DatasetChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String WikiKey
        {
            get
            {
                return _WikiKey;
            }
            set
            {
                OnWikiKeyChanging(value);
                ReportPropertyChanging("WikiKey");
                _WikiKey = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("WikiKey");
                OnWikiKeyChanged();
            }
        }
        private global::System.String _WikiKey;
        partial void OnWikiKeyChanging(global::System.String value);
        partial void OnWikiKeyChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public Nullable<global::System.Int32> Type
        {
            get
            {
                return _Type;
            }
            set
            {
                OnTypeChanging(value);
                ReportPropertyChanging("Type");
                _Type = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("Type");
                OnTypeChanged();
            }
        }
        private Nullable<global::System.Int32> _Type;
        partial void OnTypeChanging(Nullable<global::System.Int32> value);
        partial void OnTypeChanged();

        #endregion

    
        #region Navigation Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [XmlIgnoreAttribute()]
        [SoapIgnoreAttribute()]
        [DataMemberAttribute()]
        [EdmRelationshipNavigationPropertyAttribute("LandmarkDBModel", "FK_Image_Info", "Image")]
        public EntityCollection<Image> Images
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedCollection<Image>("LandmarkDBModel.FK_Image_Info", "Image");
            }
            set
            {
                if ((value != null))
                {
                    ((IEntityWithRelationships)this).RelationshipManager.InitializeRelatedCollection<Image>("LandmarkDBModel.FK_Image_Info", "Image", value);
                }
            }
        }
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [XmlIgnoreAttribute()]
        [SoapIgnoreAttribute()]
        [DataMemberAttribute()]
        [EdmRelationshipNavigationPropertyAttribute("LandmarkDBModel", "FK_Video_Info", "Video")]
        public EntityCollection<Video> Videos
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedCollection<Video>("LandmarkDBModel.FK_Video_Info", "Video");
            }
            set
            {
                if ((value != null))
                {
                    ((IEntityWithRelationships)this).RelationshipManager.InitializeRelatedCollection<Video>("LandmarkDBModel.FK_Video_Info", "Video", value);
                }
            }
        }

        #endregion

    }
    
    /// <summary>
    /// No Metadata Documentation available.
    /// </summary>
    [EdmEntityTypeAttribute(NamespaceName="LandmarkDBModel", Name="Video")]
    [Serializable()]
    [DataContractAttribute(IsReference=true)]
    public partial class Video : EntityObject
    {
        #region Factory Method
    
        /// <summary>
        /// Create a new Video object.
        /// </summary>
        /// <param name="id">Initial value of the ID property.</param>
        /// <param name="landmarkID">Initial value of the LandmarkID property.</param>
        /// <param name="iD_Dataset">Initial value of the ID_Dataset property.</param>
        public static Video CreateVideo(global::System.Int32 id, global::System.Int32 landmarkID, global::System.Int32 iD_Dataset)
        {
            Video video = new Video();
            video.ID = id;
            video.LandmarkID = landmarkID;
            video.ID_Dataset = iD_Dataset;
            return video;
        }

        #endregion

        #region Primitive Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=true, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID
        {
            get
            {
                return _ID;
            }
            set
            {
                if (_ID != value)
                {
                    OnIDChanging(value);
                    ReportPropertyChanging("ID");
                    _ID = StructuralObject.SetValidValue(value);
                    ReportPropertyChanged("ID");
                    OnIDChanged();
                }
            }
        }
        private global::System.Int32 _ID;
        partial void OnIDChanging(global::System.Int32 value);
        partial void OnIDChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 LandmarkID
        {
            get
            {
                return _LandmarkID;
            }
            set
            {
                OnLandmarkIDChanging(value);
                ReportPropertyChanging("LandmarkID");
                _LandmarkID = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("LandmarkID");
                OnLandmarkIDChanged();
            }
        }
        private global::System.Int32 _LandmarkID;
        partial void OnLandmarkIDChanging(global::System.Int32 value);
        partial void OnLandmarkIDChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Name
        {
            get
            {
                return _Name;
            }
            set
            {
                OnNameChanging(value);
                ReportPropertyChanging("Name");
                _Name = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Name");
                OnNameChanged();
            }
        }
        private global::System.String _Name;
        partial void OnNameChanging(global::System.String value);
        partial void OnNameChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String URL
        {
            get
            {
                return _URL;
            }
            set
            {
                OnURLChanging(value);
                ReportPropertyChanging("URL");
                _URL = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("URL");
                OnURLChanged();
            }
        }
        private global::System.String _URL;
        partial void OnURLChanging(global::System.String value);
        partial void OnURLChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=true)]
        [DataMemberAttribute()]
        public global::System.String Thumbnail_URL
        {
            get
            {
                return _Thumbnail_URL;
            }
            set
            {
                OnThumbnail_URLChanging(value);
                ReportPropertyChanging("Thumbnail_URL");
                _Thumbnail_URL = StructuralObject.SetValidValue(value, true);
                ReportPropertyChanged("Thumbnail_URL");
                OnThumbnail_URLChanged();
            }
        }
        private global::System.String _Thumbnail_URL;
        partial void OnThumbnail_URLChanging(global::System.String value);
        partial void OnThumbnail_URLChanged();
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [EdmScalarPropertyAttribute(EntityKeyProperty=false, IsNullable=false)]
        [DataMemberAttribute()]
        public global::System.Int32 ID_Dataset
        {
            get
            {
                return _ID_Dataset;
            }
            set
            {
                OnID_DatasetChanging(value);
                ReportPropertyChanging("ID_Dataset");
                _ID_Dataset = StructuralObject.SetValidValue(value);
                ReportPropertyChanged("ID_Dataset");
                OnID_DatasetChanged();
            }
        }
        private global::System.Int32 _ID_Dataset;
        partial void OnID_DatasetChanging(global::System.Int32 value);
        partial void OnID_DatasetChanged();

        #endregion

    
        #region Navigation Properties
    
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [XmlIgnoreAttribute()]
        [SoapIgnoreAttribute()]
        [DataMemberAttribute()]
        [EdmRelationshipNavigationPropertyAttribute("LandmarkDBModel", "FK_Video_Info", "Info")]
        public Info Info
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Video_Info", "Info").Value;
            }
            set
            {
                ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Video_Info", "Info").Value = value;
            }
        }
        /// <summary>
        /// No Metadata Documentation available.
        /// </summary>
        [BrowsableAttribute(false)]
        [DataMemberAttribute()]
        public EntityReference<Info> InfoReference
        {
            get
            {
                return ((IEntityWithRelationships)this).RelationshipManager.GetRelatedReference<Info>("LandmarkDBModel.FK_Video_Info", "Info");
            }
            set
            {
                if ((value != null))
                {
                    ((IEntityWithRelationships)this).RelationshipManager.InitializeRelatedReference<Info>("LandmarkDBModel.FK_Video_Info", "Info", value);
                }
            }
        }

        #endregion

    }

    #endregion

    
}