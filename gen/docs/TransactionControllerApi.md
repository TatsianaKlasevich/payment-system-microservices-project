# TransactionControllerApi

All URIs are relative to *http://localhost:8082*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createDepositUsingPOST**](TransactionControllerApi.md#createDepositUsingPOST) | **
POST** /payments/v1/deposit | Make deposit
[**createPaymentUsingPOST**](TransactionControllerApi.md#createPaymentUsingPOST) | **
POST** /payments/v1/payment | Make payment
[**createTransferUsingPOST**](TransactionControllerApi.md#createTransferUsingPOST) | **
POST** /payments/v1/transfer | Make transfer
[**getAllTransactionsByCardIdUsingGET**](TransactionControllerApi.md#getAllTransactionsByCardIdUsingGET) | **
GET** /payments/v1/{cardId} | Get all payment transactions by some card id
[**getAllTransactionsUsingGET**](TransactionControllerApi.md#getAllTransactionsUsingGET) | **
GET** /payments/v1/page | Show all payments by some page and sort

<a name="createDepositUsingPOST"></a>

# **createDepositUsingPOST**

> DepositResponseDTO createDepositUsingPOST(depositRequestDTO)

Make deposit

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    TransactionControllerApi apiInstance = new TransactionControllerApi(defaultClient);
    DepositRequestDTO depositRequestDTO = new DepositRequestDTO(); // DepositRequestDTO | depositRequestDTO
    try {
      DepositResponseDTO result = apiInstance.createDepositUsingPOST(depositRequestDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionControllerApi#createDepositUsingPOST");
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
**depositRequestDTO** | [**DepositRequestDTO**](DepositRequestDTO.md)| depositRequestDTO |

### Return type

[**DepositResponseDTO**](DepositResponseDTO.md)

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

<a name="createPaymentUsingPOST"></a>

# **createPaymentUsingPOST**

> PaymentResponseDTO createPaymentUsingPOST(paymentRequestDTO)

Make payment

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    TransactionControllerApi apiInstance = new TransactionControllerApi(defaultClient);
    PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(); // PaymentRequestDTO | paymentRequestDTO
    try {
      PaymentResponseDTO result = apiInstance.createPaymentUsingPOST(paymentRequestDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionControllerApi#createPaymentUsingPOST");
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
**paymentRequestDTO** | [**PaymentRequestDTO**](PaymentRequestDTO.md)| paymentRequestDTO |

### Return type

[**PaymentResponseDTO**](PaymentResponseDTO.md)

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

<a name="createTransferUsingPOST"></a>

# **createTransferUsingPOST**

> TransferResponseDTO createTransferUsingPOST(transferRequestDTO)

Make transfer

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    TransactionControllerApi apiInstance = new TransactionControllerApi(defaultClient);
    TransferRequestDTO transferRequestDTO = new TransferRequestDTO(); // TransferRequestDTO | transferRequestDTO
    try {
      TransferResponseDTO result = apiInstance.createTransferUsingPOST(transferRequestDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionControllerApi#createTransferUsingPOST");
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
**transferRequestDTO** | [**TransferRequestDTO**](TransferRequestDTO.md)| transferRequestDTO |

### Return type

[**TransferResponseDTO**](TransferResponseDTO.md)

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

<a name="getAllTransactionsByCardIdUsingGET"></a>

# **getAllTransactionsByCardIdUsingGET**

> List&lt;TransactionResponseDTO&gt; getAllTransactionsByCardIdUsingGET(cardId)

Get all payment transactions by some card id

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    TransactionControllerApi apiInstance = new TransactionControllerApi(defaultClient);
    Long cardId = 56L; // Long | cardId
    try {
      List<TransactionResponseDTO> result = apiInstance.getAllTransactionsByCardIdUsingGET(cardId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionControllerApi#getAllTransactionsByCardIdUsingGET");
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

[**List&lt;TransactionResponseDTO&gt;**](TransactionResponseDTO.md)

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

<a name="getAllTransactionsUsingGET"></a>

# **getAllTransactionsUsingGET**

> List&lt;TransactionResponseDTO&gt; getAllTransactionsUsingGET(offset, paged, pageNumber, pageSize, sortSorted, sortUnsorted, unpaged)

Show all payments by some page and sort

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8082");

    TransactionControllerApi apiInstance = new TransactionControllerApi(defaultClient);
    Long offset = 56L; // Long | 
    Boolean paged = true; // Boolean | 
    Integer pageNumber = 56; // Integer | 
    Integer pageSize = 56; // Integer | 
    Boolean sortSorted = true; // Boolean | 
    Boolean sortUnsorted = true; // Boolean | 
    Boolean unpaged = true; // Boolean | 
    try {
      List<TransactionResponseDTO> result = apiInstance.getAllTransactionsUsingGET(offset, paged, pageNumber, pageSize, sortSorted, sortUnsorted, unpaged);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionControllerApi#getAllTransactionsUsingGET");
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

[**List&lt;TransactionResponseDTO&gt;**](TransactionResponseDTO.md)

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

