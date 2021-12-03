# CardControllerApi

All URIs are relative to *http://localhost:8082*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createCardUsingPOST**](CardControllerApi.md#createCardUsingPOST) | **POST** /payments/v1/cards/ | Create card
[**deleteCardUsingDELETE**](CardControllerApi.md#deleteCardUsingDELETE) | **
DELETE** /payments/v1/cards/{cardId} | Delete card
[**findAllCardsUsingGET**](CardControllerApi.md#findAllCardsUsingGET) | **
GET** /payments/v1/cards/page | Get all cards by some page and sort
[**getCardUsingGET**](CardControllerApi.md#getCardUsingGET) | **GET** /payments/v1/cards/{cardId} | Get card by id
[**getCardsByUserIdUsingGET**](CardControllerApi.md#getCardsByUserIdUsingGET) | **
GET** /payments/v1/cards/user/{userId} | Get all cards of some user
[**updateCardUsingPUT**](CardControllerApi.md#updateCardUsingPUT) | **PUT** /payments/v1/cards/{cardId} | Update card

<a name="createCardUsingPOST"></a>

# **createCardUsingPOST**

> CardResponseDTO createCardUsingPOST(cardRequestDTO)

Create card

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    CardRequestDTO cardRequestDTO = new CardRequestDTO(); // CardRequestDTO | cardRequestDTO
    try {
      CardResponseDTO result = apiInstance.createCardUsingPOST(cardRequestDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#createCardUsingPOST");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**cardRequestDTO** | [**CardRequestDTO**](CardRequestDTO.md)| cardRequestDTO |

### Return type

[**CardResponseDTO**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="deleteCardUsingDELETE"></a>

# **deleteCardUsingDELETE**

> CardResponseDTO deleteCardUsingDELETE(cardId)

Delete card

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    Long cardId = 56L; // Long | cardId
    try {
      CardResponseDTO result = apiInstance.deleteCardUsingDELETE(cardId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#deleteCardUsingDELETE");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**cardId** | **Long**| cardId |

### Return type

[**CardResponseDTO**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**204** | No Content |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |

<a name="findAllCardsUsingGET"></a>

# **findAllCardsUsingGET**

> List&lt;CardResponseDTO&gt; findAllCardsUsingGET(offset, paged, pageNumber, pageSize, sortSorted, sortUnsorted, unpaged)

Get all cards by some page and sort

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    Long offset = 56L; // Long | 
    Boolean paged = true; // Boolean | 
    Integer pageNumber = 56; // Integer | 
    Integer pageSize = 56; // Integer | 
    Boolean sortSorted = true; // Boolean | 
    Boolean sortUnsorted = true; // Boolean | 
    Boolean unpaged = true; // Boolean | 
    try {
      List<CardResponseDTO> result = apiInstance.findAllCardsUsingGET(offset, paged, pageNumber, pageSize, sortSorted, sortUnsorted, unpaged);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#findAllCardsUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**offset** | **Long**|  | [optional]
**paged** | **Boolean**|  | [optional]
**pageNumber** | **Integer**|  | [optional]
**pageSize** | **Integer**|  | [optional]
**sortSorted** | **Boolean**|  | [optional]
**sortUnsorted** | **Boolean**|  | [optional]
**unpaged** | **Boolean**|  | [optional]

### Return type

[**List&lt;CardResponseDTO&gt;**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getCardUsingGET"></a>

# **getCardUsingGET**

> CardResponseDTO getCardUsingGET(cardId)

Get card by id

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    Long cardId = 56L; // Long | cardId
    try {
      CardResponseDTO result = apiInstance.getCardUsingGET(cardId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#getCardUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**cardId** | **Long**| cardId |

### Return type

[**CardResponseDTO**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getCardsByUserIdUsingGET"></a>

# **getCardsByUserIdUsingGET**

> List&lt;CardResponseDTO&gt; getCardsByUserIdUsingGET(userId)

Get all cards of some user

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    Long userId = 56L; // Long | userId
    try {
      List<CardResponseDTO> result = apiInstance.getCardsByUserIdUsingGET(userId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#getCardsByUserIdUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**userId** | **Long**| userId |

### Return type

[**List&lt;CardResponseDTO&gt;**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="updateCardUsingPUT"></a>

# **updateCardUsingPUT**

> CardResponseDTO updateCardUsingPUT(cardId, cardRequestDTO)

Update card

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CardControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    CardControllerApi apiInstance = new CardControllerApi(defaultClient);
    Long cardId = 56L; // Long | cardId
    CardRequestDTO cardRequestDTO = new CardRequestDTO(); // CardRequestDTO | cardRequestDTO
    try {
      CardResponseDTO result = apiInstance.updateCardUsingPUT(cardId, cardRequestDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CardControllerApi#updateCardUsingPUT");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**cardId** | **Long**| cardId |
**cardRequestDTO** | [**CardRequestDTO**](CardRequestDTO.md)| cardRequestDTO |

### Return type

[**CardResponseDTO**](CardResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

