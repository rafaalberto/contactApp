package br.com.contact.resources.network.fuel

import br.com.contact.domain.exception.NetworkException
import br.com.contact.resources.network.ContactApi
import br.com.contact.resources.network.entity.ContactResponse
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet

class ContactFuelApi : ContactApi {

    override fun findAll(): List<ContactResponse> {
        val (_, response, result) = "/contacts".httpGet().responseObject<List<ContactResponse>>()
        return when (response.statusCode) {
            200, 304 -> result.get()
            else -> throw NetworkException()
        }
    }

    override fun findById(id: Long): ContactResponse? {
        val (_, response, result) = "/contacts/$id".httpGet().responseObject<ContactResponse>()
        return when (response.statusCode) {
            200, 304 -> result.get()
            404 -> null
            else -> throw NetworkException()
        }
    }
}