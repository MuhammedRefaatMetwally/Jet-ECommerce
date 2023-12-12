package com.example.domain.features.cart.model.check_out

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CheckOutResponse(
	val session: Session? = null,
	val status: String? = null
) : Parcelable

@Parcelize
data class PaymentMethodOptions(
	val any: String? = null
) : Parcelable

@Parcelize
data class InvoiceData(
	val metadata: Metadata? = null,
	val accountTaxIds: String? = null,
	val footer: String? = null,
	val customFields: String? = null,
	val renderingOptions: String? = null,
	val description: String? = null
) : Parcelable

@Parcelize
data class CustomText(
	val afterSubmit: String? = null,
	val submit: String? = null,
	val termsOfServiceAcceptance: String? = null,
	val shippingAddress: String? = null
) : Parcelable

@Parcelize
data class AutomaticTax(
	val enabled: Boolean? = null,
	val status: String? = null
) : Parcelable

@Parcelize
data class PhoneNumberCollection(
	val enabled: Boolean? = null
) : Parcelable

@Parcelize
data class TotalDetails(
	val amountTax: Int? = null,
	val amountDiscount: Int? = null,
	val amountShipping: Int? = null
) : Parcelable

@Parcelize
data class Session(
	val metadata: Metadata? = null,
	val afterExpiration: String? = null,
	val livemode: Boolean? = null,
	val amountTotal: Int? = null,
	val mode: String? = null,
	val customerDetails: CustomerDetails? = null,
	val allowPromotionCodes: String? = null,
	val customerCreation: String? = null,
	val clientReferenceId: String? = null,
	val id: String? = null,
	val clientSecret: String? = null,
	val billingAddressCollection: String? = null,
	val shippingAddressCollection: String? = null,
	val created: Int? = null,
	val customFields: List<String?>? = null,
	val shippingOptions: List<String?>? = null,
	val consent: String? = null,
	val recoveredFrom: String? = null,
	val submitType: String? = null,
	val paymentMethodConfigurationDetails: PaymentMethodConfigurationDetails? = null,
	val customText: CustomText? = null,
	val customerEmail: String? = null,
	val paymentIntent: String? = null,
	val cancelUrl: String? = null,
	val uiMode: String? = null,
	val amountSubtotal: Int? = null,
	val objectData: String? = null,
	val status: String? = null,
	val paymentMethodCollection: String? = null,
	val shippingDetails: String? = null,
	val subscription: String? = null,
	val locale: String? = null,
	val paymentLink: String? = null,
	val consentCollection: String? = null,
	val expiresAt: Int? = null,
	val currencyConversion: String? = null,
	val phoneNumberCollection: PhoneNumberCollection? = null,
	val currency: String? = null,
	val paymentMethodOptions: PaymentMethodOptions? = null,
	val successUrl: String? = null,
	val setupIntent: String? = null,
	val invoiceCreation: InvoiceCreation? = null,
	val shippingCost: String? = null,
	val paymentMethodTypes: List<String?>? = null,
	val totalDetails: TotalDetails? = null,
	val paymentStatus: String? = null,
	val url: String? = null,
	val automaticTax: AutomaticTax? = null,
	val invoice: String? = null,
	val customer: String? = null
) : Parcelable

@Parcelize
data class Metadata(
	val city: String? = null,
	val phone: String? = null,
	val details: String? = null
) : Parcelable

@Parcelize
data class CustomerDetails(
	val address: String? = null,
	val taxIds: String? = null,
	val phone: String? = null,
	val taxExempt: String? = null,
	val name: String? = null,
	val email: String? = null
) : Parcelable

@Parcelize
data class InvoiceCreation(
	val invoiceData: InvoiceData? = null,
	val enabled: Boolean? = null
) : Parcelable

@Parcelize
data class PaymentMethodConfigurationDetails(
	val parent: String? = null,
	val id: String? = null
) : Parcelable
