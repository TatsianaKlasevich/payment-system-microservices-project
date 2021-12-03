# TransactionResponseDTO

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **BigDecimal** |  |  [optional]
**bankCode** | **String** |  |  [optional]
**creatAt** | **OffsetDateTime** |  |  [optional]
**email** | **String** |  |  [optional]
**purposeOfPayment** | **String** |  |  [optional]
**transactionId** | **Long** |  |  [optional]
**transactionType** | [**TransactionTypeEnum**](#TransactionTypeEnum) |  |  [optional]
**unp** | **Long** |  |  [optional]
**updateAt** | **OffsetDateTime** |  |  [optional]
**userId** | **Long** |  |  [optional]

## Enum: TransactionTypeEnum

Name | Value
---- | -----
DEPOSIT | &quot;DEPOSIT&quot;
PAYMENT | &quot;PAYMENT&quot;
TRANSFER | &quot;TRANSFER&quot;



