package com.madeean.testdansmultipro.data.network

class ApiService {
  private val END_POINT = "https://dev6.dansmultipro.com/api/recruitment/positions"

  private val LIST_JOB = ".json"
  val BASE_URL_LIST_JOB = "$END_POINT$LIST_JOB"

  private val DETAIL_JOB = "/"
  val BASE_URL_DETAIL_JOB = "$END_POINT$DETAIL_JOB"
}