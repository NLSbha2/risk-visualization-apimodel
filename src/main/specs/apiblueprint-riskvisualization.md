FORMAT: 1A
HOST: https://lsagw001.atradiusnet.com:9022/sc/ref-data/ref-data-api

# SC: Reference Data V2 API

API providing the capability to Search, View and Maintain Atradius Reference Data like Currencies, Languages, Sector Groupings, Juridical Reasons, Legal Statuses, Industries, Trade Groups and External Sectors.


### Apiary Document Version Details

|Version Number |Version Description |
|:---------|:---------|
|0.1|Service design for 16 services in v2 with API header change|

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
All validations are against CG_REF_CODES. Currency exchange rate has one rule when source and target currency are equal the exchange rate must be 1

Note: rules are in the proces of being implemented within the API

##  Currency [/v2/ref-data/currency]

### Get Currency List [GET /v2/ref-data/currencies{?code,language_code,validity_flag,sort,limit,offset,suppress_count_flag}]

> This service provides a list of currencies with currency name translated in the input language.

 <!-- -->
> The service returns full list or can be limited to valid only.

 <!-- -->
> Language translation if available can be returned based on the language code input. Default is English

+ Parameters

    + code: USD (optional, string)

      > Represents three digit code of the currency.

         <!-- -->
      > Field Length - 3



    + language_code: EN (optional, string )
        
        > Represents the language in which the currency name should be translated.
        
         <!-- -->
        > Field Length - 2
        
        
      
    + validity_flag: Y (optional, string)
    
        > Represents the Flag indicating whether the currency is valid or not.
        
          <!-- -->
        > Field Length - 1
         
    + sort: `currency_code+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |currency_code+| sorts the results in ascending order of their currency_code|
        |currency_code-| sorts the results in descending order of their currency_code|
        
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.     

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

        + response (Currency List Response)

### Update Currency [PUT /v2/ref-data/currency/{code}/{language_code}]

> Update Currency parameters but not validity. Separate servcie for this. This is the core language currency table. There are translations available in TBOR_CURRENCY_NAMES.

+ Parameters

    + code: NLG (required, string)

      > Maximum Field Length - 3



    + language_code: IT (required, string)
         
        > Maximum Field Length - 2




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

    + Body

    + Attributes

        + message (Update Currency Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Currency Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Currency Failed Response)

### Get Currency FX [GET /v2/ref-data/currency-fX/{currency_from_code}/{currency_to_code}/{exchange_type}{?language_code,from_date,to_date,sort,limit,offset,suppress_count_flag}]

> This service returns the exchange rate and other associated information between a recognised currency and an Atradius reporting base currency.

> Please note there are only 2 base reporting currencies EUR and GBP

+ Parameters

    + currency_from_code: EUR (required, string)

      > Represents three digit code of the currency. This is the base currency, currenly only base values are EUR and NLG

         <!-- -->
      > Field Length - 3


    + currency_to_code: USD (required, string)
        
        > Represents three digit code of the currency.
        
         <!-- -->
        > Field Length - 3
         
         
    + exchange_type: FIX (required, string)
        
        > Represents three digit code of the currency.
        
         <!-- -->
        > Field Length - 4
         
        
    + language_code: IT (optional, string )
        
        > Represents the language in which the currency name should be translated.
        
         <!-- -->
        > Field Length - 2
        
      
    + from_date: `1998-12-31T18:46:19Z` (optional, string)
    
        > Represents Date record grouping is effective from.
               
         <!-- -->
        > Date format: YYYY-MM-DD’T’HH:mi:ss’Z’
        
       

    + to_date: `2002-01-01T18:46:19Z`  (optional, string)
    
        > Represents Date record grouping is effective to. 
        
         <!-- -->
        > Date format: YYYY-MM-DD’T’HH:mi:ss’Z’
         
    + sort: `currency_from_code+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |currency_from_code+| sorts the results in ascending order of their currency_from_code|
        |currency_from_code-| sorts the results in descending order of their currency_from_code|
        |currency_to_code+| sorts the results in ascending order of their currency_to_code|
        |currency_to_code-| sorts the results in descending order of their currency_to_code|
        |exchange_type+| sorts the results in ascending order of their exchange_type|
        |exchange_type-| sorts the results in descending order of their exchange_type|
        |effective_to+| sorts the results in ascending order of their effective_to|
        |effective_to-| sorts the results in descending order of their effective_to|
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.  

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

        + response (Currency FX Response)


### Update Currency FX [PUT /v2/ref-data/currency-fx/{currency_from_code}/{currency_to_code}/{exchange_type}]

> This service loads exchange rate from a source (potentiallyinternet)

+ Parameters

    + currency_from_code: USD (required, string)
      Maximum Field Length - 3

    + currency_to_code: GBP (required, string)
      Maximum Field Length - 3

    + exchange_type: FIX (required, string)
      Maximum Field Length - 4

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

    + Body

    + Attributes

        + message (Update CurrencyFX Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update CurrencyFX Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update CurrencyFX Failed Response)

## Language [/v2/ref-data/languages/]

### Get Language and Details List[GET /v2/ref-data/languages{?source_language_code,target_language_code,sort,limit,offset,suppress_count_flag}]

> This service provides a full list of Languages for down stream processes and other components.

 <!-- -->
> With an input of a source and target will provide the translation of the source in targetted language along with few details specific to the target language.

 <!-- -->
> Language can be limited by input of language code.


+ Parameters

    + source_language_code: NL (optional, string )

      > The source language code for which the translation is to be done.

         <!-- -->
      > Field Length - 2

    + target_language_code: EN (optional, string)

      > The target language code into which the source language is translated into.

          <!-- -->
      > Field Length - 2

    + sort: `source_language_code+` (optional, string)

      > Adding +/- at the end of the parameter will indicate the sort order.

        <!-- -->
      > Multiple parameter sorting can be carried out by comma separating the parameters.
      > Sorting on the same parameter more than once is not allowed.

      |Sort Parameter  |Description | 
              |:---------|:---------|
      |source_language_code+| sorts the results in ascending order of their source_language_code|
      |source_language_code-| sorts the results in descending order of their source_language_code|
      |target_language_code+| sorts the results in ascending order of their target_language_code|
      |target_language_code-| sorts the results in descending order of their target_language_code|


    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.       

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

        + response (Language and Details List Response)

## Trade Sector [/v2/ref-data/trade-sector/]

### Get Sector Group list [GET /v2/ref-data/trade-sectors/{sector_group_type}{?nace_code,sector_group_sequence,sector_validity_flag,from_date,to_date,sort,limit,offset,suppress_count_flag}]

> This service returns a list of trade sector and their groupings.

 <!-- -->
> The sector group type must be supplied. It can retrieve all or for specified time period.

 <!-- -->
> There is no language translation at this point.

+ Parameters

    + sector_group_type: BCAT (required, string)

      > Represents the type which indicates the type of grouping.

         <!-- -->
      > Field Length - 4

    + nace_code: 2743 (optional, string)

      > Represents NACE code that identifies the Trade Sector.

          <!-- -->
      > Field Length - 4


    + sector_group_sequence: 2743  (optional, number)
    
        > Represents the sector group which the codes are grouped, level depends on type.
        
         <!-- -->
        > Field Length - 22
        
        
    + sector_validity_flag: Y (optional, string)
    
        > Represents flag indicating whether the trade code record is valid or not.
        
           <!-- -->
        > Maximum Field Length - 1 
        
        
        
    + from_date: `2010-09-29T18:46:19Z`  (optional, string)
    
        > Represents Date record grouping is effective from.
               
         <!-- -->
        > Date format: YYYY-MM-DD’T’HH:mi:ss’Z’
        
        

    + to_date: `2010-09-29T18:46:19Z`  (optional, string)
    
        > Represents Date record grouping is effective to. 
        
         <!-- -->
        > Date format: YYYY-MM-DD’T’HH:mi:ss’Z’
         
    + sort: `nace_code+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |nace_code+| sorts the results in ascending order of their nace_code|
        |nace_code-| sorts the results in descending order of their nace_code|
        |sector_group_type+| sorts the results in ascending order of their sector_group_type|
        |sector_group_type-| sorts the results in descending order of their sector_group_type|
        |effective_to+| sorts the results in ascending order of their effective_to|
        |effective_to-| sorts the results in descending order of their effective_to|
        |sector_group_sequence+| sorts the results in ascending order of their sector_group_sequence|
        |sector_group_sequence-| sorts the results in descending order of their sector_group_sequence|
        
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.      

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

        + response (Sectors Group List Response)


### Get Industry List [GET /v2/ref-data/trade-sector-group/{type}{?sequence,validity_flag,sort,limit,offset,suppress_count_flag}]

> This service retreives a reference data list of available groups for which individual codes can be mapped. It can be Industry, business category or any other group type made available.

 <!-- -->
> Returns all groups within the groups or can be filtered on input along with valid groups.

+ Parameters

    + type: ILEV  (required, string)

      > Maximum Field Length - 4


    + sequence (optional, number)
    
        > Maximum Field Length - 3
        
    + validity_flag (optional, string)
    
        > Maximum Field Length - 1
        
    + sort: `group_type+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |group_type+| sorts the results in ascending order of their group_type|
        |group_type-| sorts the results in descending order of their group_type|
        |group_sequence+| sorts the results in ascending order of their group_sequence|
        |group_sequence-| sorts the results in descending order of their group_sequence|
        
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.

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

        + response (Industry List Response)


### Create Trade Group Ref Data [POST /v2/ref-data/trade-sector-group]

> This service is to create reference data for trade group to which trade sector codes can be grouped.

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

    + Body

    + Attributes

        + message (Create Trade Group Ref data Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Trade Group Ref data Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Trade Group Ref data Failed Response)

### Get External Sectors [GET /v2/ref-data/trade-sector-external/{sector_code_set}{?sector_code,language_code,sort,limit,offset,suppress_count_flag}]

> This service retrieves reference data trade codes for a specific external trade sector set.

 <!-- -->
> The service retrieves all codes or specific codes and can be available in a specfic language.

 <!-- -->
> Trade sector sets should be converted to Atradius recognised sector codes using a separate mapping table

+ Parameters

    + sector_code_set: NACE1 (required, string)

      > Maximum Field Length - 15



    + sector_code: 38 (optional, string)
    
        > Maximum Field Length - 15
        
        
         
    + language_code: EN (optional, string)
    
        > Maximum Field Length - 2
        
    + sort: `sector_code+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |sector_code_set+| sorts the results in ascending order of their sector_code_set|
        |sector_code_set-| sorts the results in descending order of their sector_code_set|
        |sector_code+| sorts the results in ascending order of their sector_code|
        |sector_code-| sorts the results in descending order of their sector_code|
        
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.     

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

        + response (External Sector Response)


## Juridical Reason [/v2/ref-data/juridical-reason/]

### Get Juridical Reasons [GET /v2/ref-data/juridical-reasons/{?identifier,validity_flag,sort,limit,offset,suppress_count_flag}]

> This service returns a list of juridical reasons.

 <!-- -->
> It can be filtered on input to valid only or specific reasons

+ Parameters
    + identifier: 77  (optional, number)

      > Represents the unique juridical reason ID from the core table.

         <!-- -->
      > Maximum Field Length - 22



    + validity_flag: Y (optional, string)
    
        > Represents validity of the juridical reason.
        
         <!-- -->
        > Maximum Field Length - 1
    
    + sort: `abbreviation+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |identifier+| sorts the results in ascending order of their identifier|
        |identifier-| sorts the results in descending order of their identifier|
        |abbreviation+| sorts the results in ascending order of their abbreviation|
        |abbreviation-| sorts the results in descending order of their abbreviation|
        
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.      


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

        + response (Juridical Reasons Response)

### Create Juridical Reason [POST /v2/ref-data/juridical-reason]

> This service creates Reference data for juridical reasons.

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

    + Body

    + Attributes

        + message (Create Juridical Reason Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Juridical Reason Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Juridical Reason Failed Response)

### Update Juridical Reason [PUT /v2/ref-data/juridical-reason/{identifier}]

> Service to update Juridical reference data. Updating can also makes historic by updating validity flag to N

+ Parameters

    + identifier: 25 (required, number)

      > Represents the Juridical Reason Identifier.

         <!-- -->
      > Maximum Field Length - 4




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

    + Body

    + Attributes

        + message (Update Juridical Reason Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Juridical Reason Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Juridical Reason Failed Response)

## Legal Status [/v2/ref-data/legal-status/]

### Get Legal Status List [GET /v2/ref-data/legal-statuses/{?type,language_code,validity_flag,sort,limit,offset,suppress_count_flag}]

> This service retrieves list of legal status reference data.

 <!-- -->
> Translation available for certain languages using the language code and list can be limited to valid codes or individual legal status record can be returned.

+ Parameters

    + type: SOC  (optional, string)

      > Maximum Field Length - 4




    + language_code: IT  (optional, string)
    
        > Maximum Field Length - 2
        
        
        
    + validity_flag: Y (optional, string)
    
        > Maximum Field Length - 1
        
    + sort: `description+` (optional, string)
        
        > Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters. 
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |type+| sorts the results in ascending order of their type|
        |type-| sorts the results in descending order of their type|
        |description+| sorts the results in ascending order of their description|
        |description-| sorts the results in descending order of their description|
        |validity_flag+| sorts the results in ascending order of their validity_flag|
        |validity_flag-| sorts the results in descending order of their validity_flag|
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.      

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

        + response (Legal Status List Response)



### Create Legal Status [POST /v2/ref-data/legal-status]

> This service creates legal status reference data. The Legal Status is input in the language it is legally known.

 <!-- -->
> If a translation is applicable it can be added via a separate service.

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

    + Body

    + Attributes

        + message (Create Legal Status Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Legal Status Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not created successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Create Legal Status Failed Response)



### Update Legal Status [PUT /v2/ref-data/legal-status/{type}]

> This service is  to update Legal Status details. Updating the valid flag can render the flag invalid, however user can reverse this by setting flag to Y.

+ Parameters

    + type: SOC (required, string)

      > Represents the Legal Code Identifier.

         <!-- -->
      > Maximum Field Length - 4




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

    + Body

    + Attributes

        + message (Update Legal Status Request, required)

+ Response 200 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Legal Status Response)

+ Response 400 (application/json)

    + Headers

            resultcode: 2XX/4XX/5XX
            description: Results not updated successfully
            count: 2
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Update Legal Status Failed Response)


## Business Unit [/v2/ref-data/business-units]

### Get Business Units [GET /v2/ref-data/business-units{?corporate_identifier,country_identifier,unit_identifier,steering_unit_identifier,profit_loss_identifier,risk_service_unit,from_date,to_date,language_code,sort,limit,offset,suppress_count_flag}]

> Service that retreives the internal business units within the Corporate entity


+ Parameters

    + corporate_identifier: 1  (optional, number)

      > Maximum Field Length - 8


    + country_identifier: 5  (optional, number)
    
        > Maximum Field Length - 4
        
    + unit_identifier: 1 (optional, number)
    
        > Maximum Field Length - 14
    
    + steering_unit_identifier: 1 (optional, number)
    
        > Maximum Field Length - 22
    
    + profit_loss_identifier: 1 (optional, number)
    
        > Maximum Field Length - 22
        
    + risk_service_unit: 3 (optional, number)
    
        > Maximum Field Length - 6  
        
    + from_date: `2019-05-25T12:12:54Z` (optional, string)

        > Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’ 
        
         <!-- -->
        > The effective from date.
    
    + to_date: `2019-05-20T12:12:54Z` (optional, string)

        > Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’ 
        
         <!-- -->
        > The effective to date.
        
    + language_code: EN (optional, string)

        > Maximum Field Length - 2.
        
         <!-- -->
        > The two digit language code.
    
    + sort: corporate_identifier (optional, string)
        
        > Parameters allowed to sort on are corporate_identifier, unit_name and unit_identifier. Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters.
        
        > The result is sorted by corporate_identifier by default. Default sort order is ascending.
        
        <!-- -->
        
        > <b>+</b> sorts in ascending order and <b>-</b> sorts in descending order.
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |corporate_identifier+| sorts the results in ascending order of their corporate identifier.|
        |corporate_identifier-| sorts the results in descending order of their corporate identifier.|
        |unit_name+| sorts the results in ascending order of their unit name.|
        |unit_name-| sorts the results in descending order of their unit name.|
        |unit_identifier+| sorts the results in ascending order of their unit identifier.|
        |unit_identifier-|sorts the results in descending order of their unit identifier.|
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.

    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.         
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
            count: 100
            resultCount: 2
            offset: 0
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Get Business Unit Response)

### Get Steering Units [GET /v2/ref-data/steering-units{?identifier,from_date,to_date,language_code,sort,limit,offset,suppress_count_flag}]

> Service that retreives a list of steering units. Within the data model business units are attached to these steering units


+ Parameters


    + identifier: 1 (optional, number)
    
        > Maximum Field Length - 22
    
 
    + from_date: `2019-05-25T12:12:54Z` (optional, string)
 
        > Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’ 
        
         <!-- -->
        > The effective from date.
    
    + to_date: `2019-05-20T12:12:54Z` (optional, string)
 
        > Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’ 
        
         <!-- -->
        > The effective to date.
        
    + language_code: EN (optional, string)
 
        > Maximum Field Length - 2.
        
         <!-- -->
        > The two digit language code.
    
    + sort: identifier (optional, string)
        
        > Parameters allowed to sort on are Identifier, Description. Adding +/- at the end of the parameter will indicate the sort order.
        
        <!-- -->
        > Multiple parameter sorting can be carried out by comma separating the parameters.
        
        > The result is sorted by corporate_identifier by default. Default sort order is ascending.
        
        <!-- -->
        
        > <b>+</b> sorts in ascending order and <b>-</b> sorts in descending order.
        > Sorting on the same parameter more than once is not allowed.
        
        |Sort Parameter  |Description | 
        |:---------|:---------|
        |identifier+| sorts the results in ascending order of their steering identifier.|
        |identifier-| sorts the results in descending order of their steering identifier.|
        |description+| sorts the results in ascending order of their steering unit name.|
        |description-| sorts the results in descending order of their steering unit name.|
        
    
    + limit: 2 (optional, number)
      
       > Allows to set the limit for number of results in a single request.
       
    + offset: 0 (optional , number)
     
        > Sets the offset for next set.
 
    + suppress_count_flag : Y (enum[Y, N] , optional)
     
        > A flag to indicate whether the total count of record should be calcualted or not.         
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
            count: 100
            resultCount: 2
            offset: 0
            responseTimeStamp: Thu, 15 Nov 2018 08:12:33 CET

    + Attributes

        + response (Get Steering Unit Response)


# Data Structures


## Currency List Response (object)
+ data (Currency List, required)

## Currency List (object)
- currencies(array, fixed-type)
    - (Currency List1)
    - (Currency List2)

## Currency List1 (object)

+ currency_code: USD (string)
  Maximum Field Length - 3

+ validity_flag: Y (string)
  Maximum Field Length - 1

+ language_code: EN (string)
  Maximum Field Length - 2

+ currency_name: US Dollars (string)
  Maximum Field Length - 35

+ currency_symbol_indicator: $ (string, nullable)
  Maximum Field Length - 1

+ euro_fixed_flag: Y (string, nullable)
  Maximum Field Length - 1

+ integer_flag: Y (string, nullable)
  Maximum Field Length - 1

+ au_truncated_amount: 1000 (number, nullable)
  Maximum Field Length - 22

+ standard_atradius_flag: Y (string, nullable)
  Maximum Field Length - 1

## Currency List2 (object)

+ currency_code: USD (string)
  Maximum Field Length - 3

+ validity_flag: N (string)
  Maximum Field Length - 1

+ language_code: EN (string)
  Maximum Field Length - 2

+ currency_name: US Dollars (string)
  Maximum Field Length - 35

+ currency_symbol_indicator: $ (string, nullable)
  Maximum Field Length - 1

+ euro_fixed_flag: N (string, nullable)
  Maximum Field Length - 1

+ integer_flag: N (string, nullable)
  Maximum Field Length - 1

+ au_truncated_amount: 500 (number, nullable)
  Maximum Field Length - 22

+ standard_atradius_flag: N (string, nullable)
  Maximum Field Length - 1


## Update Currency Request  (object)
- payload (updateCurrency, fixed-type)

##  updateCurrency (object)

+ standard_atradius_flag: Y (string, optional)
  Maximum Field Length - 1

+ currency_name: US Dollars (string, optional)
  Maximum Field Length - 35

+ currency_symbol_indicator: $ (string, optional)
  Maximum Field Length - 2

+ euro_fixed_flag: Y (string, nullable)
  Maximum Field Length - 1

+ integer_flag: Y (string, nullable)
  Maximum Field Length - 1

+ au_truncated_amount: 1000 (number, nullable)
  Maximum Field Length - (23,8)

+ valid_flag: Y (string, nullable)

## Update Currency Response(object)

+ data (Update Currency, required)

## Update Currency (object)
- acknowledgement(Update Currency Response1, fixed-type)

## Update Currency Response1 (object)

+ message: Currency Updated (string)
  Maximum Field Length - 100

## Update Currency Failed Response(object)

+ data (Update Currency Failed, required)

## Update Currency Failed (object)
- acknowledgement(Update Currency Failed Response1, fixed-type)

## Update Currency Failed Response1 (object)

+ message: Update Currency failed (string)
  Maximum Field Length - 100


## Update CurrencyFX Request (object)
+ payload(Update CurrencyFX Payload, required)

## Update CurrencyFX Payload (object)

+ effective_from: `2010-09-29T18:46:19Z` (string, required)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2010-09-29T18:46:19Z` (string, required)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ exchange_amount: 2.111 (number, required)
  Maximum Field Length - 32

## Update CurrencyFX Response(object)

+ data (Update CurrencyFX, required)

## Update CurrencyFX (object)
- acknowledgement(Update CurrencyFX Response1, fixed-type)

## Update CurrencyFX Response1 (object)

+ message: Currency FX Updated (string)
  Maximum Field Length - 100

## Update CurrencyFX Failed Response(object)

+ data (Update CurrencyFX Failed, required)

## Update CurrencyFX Failed (object)
- acknowledgement(Update CurrencyFX Failed Response1, fixed-type)

## Update CurrencyFX Failed Response1 (object)

+ message: Update Currency FX failed (string)
  Maximum Field Length - 100


## Currency FX Response (object)
+ data (Currency FX, required)

## Currency FX (object)
- currencies_fx(array, fixed-type)
    - (CurrencyFX List1)
    - (CurrencyFX List2)

## CurrencyFX List1 (object)

+ language_code: IT (string)
  Maximum Field Length - 2

+ language_name: Italiano (string)
  Maximum Field Length - 25

+ currency_from_code: EUR (string)
  Maximum Field Length - 3

+ currency_from_name: Euro (string)
  Maximum Field Length - 35

+ currency_to_code: USD (string)
  Maximum Field Length - 3

+ currency_to_name: Dollaro USA (string)
  Maximum Field Length - 35

+ exchange_type: FIX (string)
  Maximum Field Length - 4

+ exchange_type_description: Fixed (string, nullable)
  Maximum Field Length - 240

+ exchange_amount: 1.2 (number)
  Maximum Field Length - 23

+ effective_from: `1998-12-22T23:23:19Z` (string)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `1998-12-31T00:00:01` (string)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

## CurrencyFX List2 (object)

+ language_code: IT (string)
  Maximum Field Length - 2

+ language_name: Italiano (string)
  Maximum Field Length - 25

+ currency_from_code: EUR (string)
  Maximum Field Length - 3

+ currency_from_name: Euro (string)
  Maximum Field Length - 35

+ currency_to_code: USD (string)
  Maximum Field Length - 3

+ currency_to_name: Dollaro USA (string)
  Maximum Field Length - 35

+ exchange_type: FIX (string)
  Maximum Field Length - 4

+ exchange_type_description: Fixed (string, nullable)
  Maximum Field Length - 240

+ exchange_amount: 1.2 (number)
  Maximum Field Length - 23

+ effective_from: `1998-12-31T23:23:19Z` (string)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2002-01-01T00:00:01` (string)
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’


## Industry List Response (object)
+ data (Industry List, required)

## Industry List (object)
- industry_sector_list(array, fixed-type)
    - (industry List1)
    - (industry List2)

## industry List1 (object)

+ group_type: ILEV (string)
  Maximum Field Length - 4

+ group_type_description: Industry Level (string)
  Maximum Field Length - 100

+ group_sequence: 1 (number)
  Maximum Field Length - 3

+ group_name: Agriculture (string)
  Maximum Field Length - 35

+ group_validity_flag: Y (string)
  Maximum Field Length - 1

## industry List2 (object)

+ group_type: ILEV (string)
  Maximum Field Length - 4

+ group_type_description: Industry Level (string)
  Maximum Field Length - 100

+ group_sequence: 2 (number)
  Maximum Field Length - 3

+ group_name: Food (string)
  Maximum Field Length - 35

+ group_validity_flag: Y (string)
  Maximum Field Length - 1

## Create Juridical Reason Request (object)
- payload (createJuridicalReason, fixed-type)

##  createJuridicalReason (object)

+ juridical_reason_description: Schuldsanering (string, required)
  Maximum Field Length - 80

+ juridical_reason_abbreviation: SCH (string, required)
  Maximum Field Length - 5

## Create Juridical Reason Response (object)

+ data (Create Juridical Reason, required)

## Create Juridical Reason (object)
- acknowledgement(Create Juridical Reason Response1, fixed-type)

## Create Juridical Reason Response1 (object)

+ message: Juridical Reason Created (string)
  Maximum Field Length - 100

## Create Juridical Reason Failed Response (object)

+ data (Create Juridical Reason Failed, required)

## Create Juridical Reason Failed (object)
- acknowledgement(Create Juridical Reason Failed Response1, fixed-type)

## Create Juridical Reason Failed Response1 (object)

+ message: Creation of Juridical Reason failed (string)
  Maximum Field Length - 100

## Create Legal Status Request (object)
- payload (createLegalStatus, fixed-type)

##  createLegalStatus (object)

+ legal_status_type: BVP (string, required)
  Maximum Field Length - 4

+ legal_status_description: Business Category (string, required)
  Maximum Field Length - 80

+ category_code: U (string, nullable)
  Maximum Field Length - 1

## Create Legal Status Response (object)

+ data (Create Legal Status, required)

## Create Legal Status (object)
- acknowledgement(Create Legal Status Response1, fixed-type)

## Create Legal Status Response1 (object)

+ message: Legal Status Created (string)
  Maximum Field Length - 100

## Create Legal Status Failed Response (object)

+ data (Create Legal Status Failed, required)

## Create Legal Status Failed (object)
- acknowledgement(Create Legal Status Failed Response1, fixed-type)

## Create Legal Status Failed Response1 (object)

+ message: Creation of Legal Status failed (string)
  Maximum Field Length - 100

## Create Trade Group Ref data Request (object)
- payload (createTradeGroupRefData, fixed-type)

##  createTradeGroupRefData (object)

+ sector_group_type: BCAT (string, required)
  Maximum Field Length - 4

+ sector_group_name: `Production-Chemicals` (string, required)
  Maximum Field Length - 35

## Create Trade Group Ref data Response (object)

+ data (Create Trade Group Ref data, required)

## Create Trade Group Ref data (object)
- acknowledgement(Create Trade Group Ref data Response1, fixed-type)

## Create Trade Group Ref data Response1 (object)

+ message: Trade Group Ref data Created (string)
  Maximum Field Length - 100

## Create Trade Group Ref data Failed Response (object)

+ data (Create Trade Group Ref data Failed, required)

## Create Trade Group Ref data Failed (object)
- acknowledgement(Create Trade Group Ref data Failed Response1, fixed-type)

## Create Trade Group Ref data Failed Response1 (object)

+ message: Creation of Trade Group Ref data failed (string)
  Maximum Field Length - 100

## Language and Details List Response (object)
+ data (Language and Details List, required)

## Language and Details List (object)
- languages_and_details(array, fixed-type)
    - (Language and Details List1)
    - (Language and Details List2)

## Language and Details List1 (object)

+ source_language_code: NL (string)
  Maximum Field Length - 2

+ target_language_name: Engels (string)
  Maximum Field Length - 35

+ target_language_code: EN (string)
  Maximum Field Length - 2

+ decimal_indicator: , (string)
  Maximum Field Length - 1

+ thousand_indicator: . (string)
  Maximum Field Length - 1

+ non_latin_flag: Y (string, nullable)
  Maximum Field Length - 1

## Language and Details List2 (object)

+ source_language_code: EN (string)
  Maximum Field Length - 2

+ target_language_name: English (string)
  Maximum Field Length - 35

+ target_language_code: EN (string)
  Maximum Field Length - 2

+ decimal_indicator: . (string)
  Maximum Field Length - 1

+ thousand_indicator: , (string)
  Maximum Field Length - 1

+ non_latin_flag: N (string, nullable)
  Maximum Field Length - 1

## Sectors Group List Response (object)
+ data (Sector Group List, required)

## Sector Group List (object)
- sector_group_list(array, fixed-type)
    - (Sector Group List1)
    - (Sector Group List2)

## Sector Group List1 (object)

+ nace_code: 2743 (string)
  Maximum Field Length - 4

+ sector_name: Casting of Steel (string)
  Maximum Field Length - 35

+ sector_validity_flag: Y (string)
  Maximum Field Length - 1

+ sector_group_type: BCAT (string)
  Maximum Field Length - 4

+ sector_group_description: Business category (string, nullable)
  Maximum Field Length - 100

+ sector_group_sequence: 2743 (number)
  Maximum Field Length - 22

+ sector_group_name: `Production-Chemicals` (string)
  Maximum Field Length - 35

+ sector_group_validity_flag: Y (string)
  Maximum Field Length - 1

+ effective_from : `2010-09-29T18:46:19Z`
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2010-09-29T18:46:19Z`
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

## Sector Group List2 (object)

+ nace_code: 2743  (string)
  Maximum Field Length - 4

+ sector_name: Casting of Iron (string)
  Maximum Field Length - 35

+ sector_validity_flag: Y (string)
  Maximum Field Length - 1

+ sector_group_type: BCAT (string)
  Maximum Field Length - 4

+ sector_group_description: Industry Level (string, nullable)
  Maximum Field Length - 100

+ sector_group_sequence: 2 (number)
  Maximum Field Length - 22

+ sector_group_name: `Production-Agriculture` (string)
  Maximum Field Length - 35

+ sector_group_validity_flag: N (string)
  Maximum Field Length - 1

+ effective_from : `2010-09-29T18:46:19Z`
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2010-09-29T18:46:19Z`
  Date Format: YYYY-MM-DD’T’HH:mi:ss’Z’

## External Sector Response (object)
+ data (External Sector, required)

## External Sector (object)
- external_sectors(array, fixed-type)
    - (External Sector1)
    - (External Sector2)

## External Sector1 (object)

+ sector_code_set: NACE1(string)
  Maximum Field Length - 3

+ sector_classification: SIC CODE (string,nullable)
  Maximum Field Length - 240

+ sector_code: 38(string)
  Maximum Field Length - 2

+ short_description: Waste collection (string)
  Maximum Field Length - 35

+ full_description: Waste collection (string, nullable)
  Maximum Field Length - 150

+ language Code: EN (string)
  Maximum Field Length - 2

## External Sector2 (object)

+ sector_code_set: NACE2(string)
  Maximum Field Length - 3

+ sector_classification: SPANISH NACE (string,nullable)
  Maximum Field Length - 240

+ sector_code: 37 (string)
  Maximum Field Length - 2

+ short_description: Materials recovery (string)
  Maximum Field Length - 35

+ full_description: Materials recovery (string, nullable)
  Maximum Field Length - 150

+ language Code: FR (string)
  Maximum Field Length - 2

## Juridical Reasons Response (object)
+ data (Juridical Reason List, required)

## Juridical Reason List (object)
- juridical_reasons (array, fixed-type)
    - (Juridical Reason List1)
    - (Juridical Reason List2)

## Juridical Reason List1 (object)

+ identifier: 77 (number)
  Maximum Field Length - 22

+ abbreviation: SCH (string, nullable)
  Maximum Field Length - 5

+ description: Schuldsanering (string)
  Maximum Field Length - 80

+ validity_flag: Y (string)
  Maximum Field Length - 1

## Juridical Reason List2 (object)

+ identifier: 77 (number)
  Maximum Field Length - 22

+ abbreviation: ACON (string, nullable)
  Maximum Field Length - 5

+ description: assemblee concordataire (string)

+ validity_flag: Y (string)
  Maximum Field Length - 1


## Update Juridical Reason Request (object)
- payload (updateJuridicalReason, fixed-type)

##  updateJuridicalReason (object)

+ juridical_reason_description: Anschlusskonkursverfahren (string, optional)
  Maximum Field Length - 80

+ juridical_reason_validity_flag: Y (string, optional)
  Maximum Field Length - 1

+ juridical_reason_abbreviation: ACON (string, nullable)
  Maximum Field Length - 5

## Update Juridical Reason Response(object)

+ data (Update Juridical Reason, required)

## Update Juridical Reason (object)
- acknowledgement(Update Juridical Reason Response1, fixed-type)

## Update Juridical Reason Response1 (object)

+ message: Juridical Reason Updated (string)

## Update Juridical Reason Failed Response(object)

+ data (Update Juridical Reason Failed, required)

## Update Juridical Reason Failed (object)
- acknowledgement(Update Juridical Reason Failed Response1, fixed-type)

## Update Juridical Reason Failed Response1 (object)

+ message: Update Juridical Reason failed (string)

## Update Legal Status Request (object)
- payload (updateLegalStatus, fixed-type)

##  updateLegalStatus (object)

+ validity_flag: Y (string, optional)
  Maximum Field Length - 1

+ legal_status_description: Private limited company (string, nullable)
  Maximum Field Length - 80

+ category_code: P (string, nullable)
  Maximum Field Length - 1

## Update Legal Status Response(object)

+ data (Update Legal Status, required)

## Update Legal Status (object)
- acknowledgement(Update Legal Status Response1, fixed-type)

## Update Legal Status Response1 (object)

+ message: Legal Status Updated (string)

## Update Legal Status Failed Response(object)

+ data (Update Legal Status Failed, required)

## Update Legal Status Failed (object)
- acknowledgement(Update Legal Status Failed Response1, fixed-type)

## Update Legal Status Failed Response1 (object)

+ message: Update Legal Status failed (string)

## Legal Status List Response (object)
+ data (Legal Status List, required)

## Legal Status List (object)
- legal_status_list (array, fixed-type)
    - (Legal Status List1)
    - (Legal Status List2)

## Legal Status List1 (object)

+ type: SOC (string)
  Maximum Field Length - 4

+ description: Joint venture (string, nullable)
  Maximum Field Length - 80

+ language_code: ES (string)
  Maximum Field Length - 2

+ language_name: Spanish (string)
  Maximum Field Length - 35

+ local_description: Private limited company (string)
  Maximum Field Length - 80

+ validity_flag: Y (string)
  Maximum Field Length - 1

+ category_code: P (string, nullable)
  Maximum Field Length - 4

## Legal Status List2 (object)

+ type: SOC (string)
  Maximum Field Length - 4

+ description: Incorporated (string, nullable)
  Maximum Field Length - 80

+ language_code: IT (string)
  Maximum Field Length - 2

+ language_name: Italian (string)
  Maximum Field Length - 35

+ local_description: Incorporated (string)
  Maximum Field Length - 80

+ validity_flag: Y (string)
  Maximum Field Length - 1

+ category_code: U (string, nullable)
  Maximum Field Length - 4


## Update Trade Sector Grouping Allocation Response(object)

+ data (Update Trade Sector Grouping Allocation, required)

## Update Trade Sector Grouping Allocation (object)
- acknowledgement(Update Trade Sector Grouping Allocation Response1, fixed-type)

## Update Trade Sector Grouping Allocation Response1 (object)

+ message: Trade Sector Grouping Allocation Updated (string)
  Maximum Field Length - 100

## Update Trade Sector Grouping Allocation Failed Response(object)

+ data (Update Trade Sector Grouping Allocation Failed, required)

## Update Trade Sector Grouping Allocation Failed (object)
- acknowledgement(Update Trade Sector Grouping Allocation Failed Response1, fixed-type)

## Update Trade Sector Grouping Allocation Failed Response1 (object)

+ message: Update Trade Sector Grouping Allocation failed (string)
  Maximum Field Length - 100

## Get Business Unit Response (object)
+ data (Get Business Unit Response List, required)

##  Get Business Unit Response List (object)
+ corporate_business_units(array,fixed-type)
    - (Get Business Unit Response List1)
    - (Get Business Unit Response List2)

## Get Business Unit Response List1 (object)

+ corporate_identifier: 1  (required, number)
  Maximum Field Length - 8

+ corporate_name: NCM Holding NV  (required, string)
  Maximum Field Length - 35

+ name_language_code: FR (optional, string)
  Maximum Field Length - 2.

+ country_identifier: 5  (required, number)
  Maximum Field Length - 4

+ country_name: Netherlands  (required, string)
  Maximum Field Length - 35

+ country_language_code: EN (optional, string)
  Maximum Field Length - 2.

+ unit_identifier: 1 (required, number)
  Maximum Field Length - 14

+ unit_name: UK Global (required, string)
  Maximum Field Length - 35

+ steering_unit_identifier: 1 (optional, number)
  > Maximum Field Length - 22

+ steering_unit_description: 1 (nullable, string)
  > Maximum Field Length - 50

+ profit_loss_identifier: 1 (optional, number)
  > Maximum Field Length - 22

+ profit_loss_description (nullable, string)
  > Maximum Field Length - 50

+ risk_service_unit: 3 (required, number)
  Maximum Field Length - 6

+ effective_from: `2019-05-25T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2019-05-20T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ denote_sign: B (required, string)
  Maximum Field Length - 1

+ cost_centre: 1001 (required, string)
  Maximum Field Length - 4

+ customiser_flag:  Y (required, string)
  Maximum Field Length - 1

+ system_user_sign1:  123 (required, number)
  Maximum Field Length - 22

+ system_user_sign2:  345 (required, number)
  Maximum Field Length - 22

## Get Business Unit Response List2 (object)

+ corporate_identifier: 1  (required, number)
  Maximum Field Length - 8

+ corporate_name: NCM Holding NV  (required, string)
  Maximum Field Length - 35

+ name_language_code: FR (optional, string)
  Maximum Field Length - 2.

+ country_identifier: 5  (required, number)
  Maximum Field Length - 4

+ country_name: Netherlands  (required, string)
  Maximum Field Length - 35

+ country_language_code: EN (optional, string)
  Maximum Field Length - 2.

+ unit_identifier: 1 (required, number)
  Maximum Field Length - 14

+ unit_name: UK International (required, string)
  Maximum Field Length - 35

+ steering_unit_identifier: 1 (optional, number)
  > Maximum Field Length - 22

+ steering_unit_description: 1 (nullable, string)
  > Maximum Field Length - 50

+ profit_loss_identifier: 1 (optional, number)
  > Maximum Field Length - 22

+ profit_loss_description (nullable, string)
  > Maximum Field Length - 50

+ risk_service_unit: 3 (required, number)
  Maximum Field Length - 6

+ effective_from: `2019-05-25T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2019-05-20T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ denote_sign: N (required, string)
  Maximum Field Length - 1

+ cost_centre: 1101 (required, string)
  Maximum Field Length - 4

+ customiser_flag:  Y (required, string)
  Maximum Field Length - 1

+ system_user_sign1:  123 (required, number)
  Maximum Field Length - 22

+ system_user_sign2:  345 (required, number)
  Maximum Field Length - 22



## Get Steering Unit Response (object)
+ data (Get Steering Unit Response List, required)

##  Get Steering Unit Response List (object)
+ steering_units(array,fixed-type)
    - (Get Steering Unit Response List1)
    - (Get Steering Unit Response List2)

## Get Steering Unit Response List1 (object)

+ identifier: 1 (optional, number)
  > Maximum Field Length - 22

+ description: Global (nullable, string)
  > Maximum Field Length - 50

+ language_code: FR (optional, string)
  Maximum Field Length - 2.

+ effective_from: `2019-05-25T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2019-05-20T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

## Get Steering Unit Response List2 (object)

+ identifier: 2 (optional, number)
  > Maximum Field Length - 22

+ description: Nordic (nullable, string)
  > Maximum Field Length - 50

+ language_code: FR (optional, string)
  Maximum Field Length - 2.

+ effective_from: `2019-05-25T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

+ effective_to: `2019-05-20T12:12:54Z` (required, string)
  Maximum Field Length - 25. Date Format : YYYY-MM-DD’T’HH:mi:ss’Z’

## metadata
+ totalCount: 1 (number, required)
+ resultCount: 1 (number, required)
+ offset: 0 (number, required)

#Group Appendix

##Trade sector Grouping types

|Type |Description |
|:----|:---------|
|BCAT|Business Category|
|ILEV|Industry Level|
|TGRP|Trade Sector Group|
|SUBS|CyC Subsector|
|SECT|CyC Sector|
