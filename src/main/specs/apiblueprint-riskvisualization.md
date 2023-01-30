FORMAT: 1A
HOST:  - url: https://lsagw001.atradiusnet.com:9022/sc/org-data/risk-data-api

# SC: Risk Visualization V1 API

API providing the capability to retrieve, risk relationships between organizations within Atradius.


### Apiary Document Version Details

|Version Number | Version Description                                       |
|:---------|:----------------------------------------------------------|
|0.1| Service design for 1 service in v1 with API header change |

### Request Headers

|Header |Sample Value |Mandatory |Description | 
|:---------|:---------|:-------|:-------|
|Content-Type|application/json|Yes|Media type of the resource.|
|Authorization |"Bearer 527TIUuRrkpjwepX24ZSng.."<br> YWxhZGRpbjpvcGVuc2VzYW1l...<br> NMExhZGRpbjtyuGVuc2UzYx4.. |Yes |OAuth 2 Authorization.| 
|Atradius-Origin-User-Id |pt.John01 |Yes |Name of the user who is performing an action in a system (Note this is normally the login ID coming from the LDAP.|
|Atradius-Origin-Application |BBT |Yes |Identification of the application which invoked the service.|
|Atradius-Origin-Service |AnalysisDecisions |Yes |The name of origin (parent) service which is invoked.| 
|Atradius-Invocation-Service |http://atradius.com/bbt/OrganisationSearch |Yes |The invocation service URL.| 
|Atradius-Message-Timestamp |Thu, 15 Nov 2018 08:12:31 CET |Yes  |The timestamp when the client is invoked the origin service.| 
|Atradius-Message-Id|efeeehfn73849grre|Yes|Unique identifier of the message that has been send.|
|Atradius-Correlation-Id |5c4c8sda |Yes  |Unique identifier of a transaction over various applications / components.| 
|Atradius-Concurrency-Control-Version|1|No| A placeholder were we can store a string with relevant information to perform in the data store the verification of the optimistic concurrency control. Block to capture the information which might be required as part of the Optimistic concurrency control (OCC) which is a concurrency control method applied to transactional systems.|

### Response Headers

|Header |Sample Value |Mandatory |Description                                       | 
|:---------|:---------|:-------|:-------|
|resultcode |2XX/4XX/5XX |No |A response that can be returned to an application, <br> even to indicate that the request was successful.| 
|description |Results retrieved successfully |No |A description that corresponds with the response code, even to indicate that the request was successful. | 
|count |2 |No |The number of records in a response.|
|responseTimeStamp |Thu, 15 Nov 2018 08:12:33 CET |No |The timestamp when the client receives the response.| 

### GET services with effective dates
|FROM DATE |TO DATE |API Orchestration Description  |
|:---------|:---------|:-------|:-------|
|Null      |Null   | All the records irrespective of the dates are fetched.|
|Date   |Null   |All the records where the <b>FROM DATE</b> from the input falls between the <i>effective from</i> and <i>effective to</i> dates in the table are fetched.|
|Null   |Date   |Not Supported|
|Date   |Date   |All the records where the <b>FROM DATE</b> and the <b>TO DATE</b> from the input falls between the <i>effective from</i> and <i>effective to</i> dates in the table are fetched.|

# Group Reference Data API

## Data Rules and Validations
All validations are against valid ORG_IDs. 

Note: rules are in the process of being implemented within the API

##  Risk Relationships [/v1/risk-data-api/risk-relationships:]

### Get Risk Relationships [GET /v1/sc/org-data/risk-data-api{?organization_id,language_code,include_information_links,limit,offset,suppress_count_flag}]

> This API provides a the organization risk relationships data of the organization
translated in the input language.

 <!-- -->
>The service returns full hierarchical data of organization risks.

 <!-- -->
> Language translation if available can be returned based on the language code input. Default is English

+ Parameters

    + organization_id: 1234 (required, string)

      > Represents organization ID of a valid Atradius organization.

         <!-- -->
      > Field Length - 16

    + date: 2023-01-29T00:00:00Z (optional, string )

    > Point in time of the returned data. ISO8601 format [YYYY-MM-DDTHH:mi:ssZ]

           <!-- -->
    > Field Length - 16
    + language_code: EN (optional, string )
        
        >   Represents the language in which the currency name should be translated.
        
               <!-- -->
        >   Field Length - 2
          + include_information_links: true (optional, boolean)

         >   true if information links are to be included.
    
      + limit: 2 (optional, number)
      
       >         Allows to set the limit for number of results in a single request.
       
      + offset: 0 (optional , number)
     
        >           Sets the offset for next set.

      + suppress_count_flag : Y (enum[Y, N] , optional)
     
        >             A flag to indicate whether the total count of record should be calculated or not.     

+ Request (application/json)

    + Headers

            Authorization: "Bearer 527TIUuRrkpjwepX24ZSng.." YWxhZGRpbjpvcGVuc2VzYW1l...NMExhZGRpbjtyuGVuc2UzYx4..
            Atradius-Origin-User-Id: pt.John01
            Atradius-Origin-Application: BBT
            Atradius-Origin-Service: AnalysisDecisions
            Atradius-Invocation-Service: http://atradius.com/bbt/OrganisationSearch
            Atradius-Message-Timestamp: Thu, 15 Nov 2018 08:12:31 CET
            Atradius-Message-Id: 44868hgddhj
            Atradius-Correlation-Id: 5c4c8sda
            Atradius-Concurrency-Control-Version: 2

+ Response 200 (application/json)

    + Headers

            resultCode: 2XX/4XX/5XX
            description: Results retrieved successfully
            count: 2
            resultCount: 2
            offset: 0
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Risk Relationships Response)


