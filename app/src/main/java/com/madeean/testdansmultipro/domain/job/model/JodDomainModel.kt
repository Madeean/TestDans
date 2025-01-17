package com.madeean.testdansmultipro.domain.job.model

data class JobDomainModel(
  val id: String,
  val type: String,
  val url: String,
  val createdAt: String,
  val company: String,
  val companyUrl: String,
  val location: String,
  val title: String,
  val description: String,
  val howToApply: String,
  val companyLogo: String,
)
