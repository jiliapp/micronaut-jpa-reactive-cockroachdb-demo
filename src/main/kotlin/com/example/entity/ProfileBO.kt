package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.micronaut.core.annotation.Introspected
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "profile", schema = "\"user\"")
@org.hibernate.annotations.Table(appliesTo = "profile", comment = "账户资料")
@Introspected
data class ProfileBO (
    @field:JsonSerialize(using = ToStringSerializer::class)
    @field:Comment("用户ID")
    var  userId:Long?=null,
    @Id @field:JsonSerialize(using = ToStringSerializer::class)
    @field:Comment("用户ID")
    var  id:Long?=null,
    ){

}
