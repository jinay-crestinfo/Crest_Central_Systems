package com.crestinfosystems_jinay.crestcentralsystems.model

data class user(
    val alternate_contact_number: String = "",
    val contact_number: String= "",
    val designation_id: Int= 0,
    val designation_name: String= "",
    val email: String= "",
    val employee_number: String= "",
    val first_name: String= "",
    val id: Int= 0,
    val joining_date: String= "",
    val last_name: String= "",
    val onboarding_status: String= "",
    val reportingTo: ReportingTo,
    val role: Int= 0,
    val role_name: String= "",
    val status: String= ""
)
data class ReportingTo(
    val first_name: String,
    val id: Int,
    val last_name: String
)