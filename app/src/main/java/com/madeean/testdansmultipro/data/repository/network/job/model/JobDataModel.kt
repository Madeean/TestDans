package com.madeean.testdansmultipro.data.repository.network.job.model

import com.madeean.testdansmultipro.domain.job.model.JobDomainModel
import com.madeean.testdansmultipro.presentation.util.requireIfNull
import kotlinx.serialization.Serializable

@Serializable
data class JobDataModel(
  val id: String?,
  val type: String?,
  val url: String?,
  val created_at: String?,
  val company: String?,
  val company_url: String?,
  val location: String?,
  val title: String?,
  val description: String?,
  val how_to_apply: String?,
  val company_logo: String?,
){
  companion object{
    fun transforms(models: List<JobDataModel?>?): List<JobDomainModel>{
      return models?.map {
        if(it != null) transform(it) else JobDomainModel("","","","","","","","","","","")
      }?: listOf()
    }

    fun transform(model: JobDataModel?): JobDomainModel{
      return JobDomainModel(
        id = model?.id.requireIfNull(),
        type = model?.type.requireIfNull(),
        url = model?.url.requireIfNull(),
        createdAt = model?.created_at.requireIfNull(),
        company = model?.company.requireIfNull(),
        companyUrl = model?.company_url.requireIfNull(),
        location = model?.location.requireIfNull(),
        title = model?.location.requireIfNull(),
        description = model?.description.requireIfNull(),
        howToApply = model?.how_to_apply.requireIfNull(),
        companyLogo = model?.company_logo.requireIfNull()
      )
    }
  }
}
