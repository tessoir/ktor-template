package com.example.shared.ext

import com.example.shared.dto.ErrorResponse
import io.github.smiley4.ktorswaggerui.data.anyOf
import io.github.smiley4.ktorswaggerui.dsl.routes.OpenApiMultipartBody
import io.github.smiley4.ktorswaggerui.dsl.routes.OpenApiRoute
import io.ktor.http.HttpStatusCode
import kotlin.reflect.KType

/**
 * Add 200 response body schema to the route documentation
 *
 * @param T Response type
 * @param description Response description
 * @param bodyDescription Response body description
 */
inline fun <reified T> OpenApiRoute.successResponse(
    description: String? = null,
    bodyDescription: String? = null,
): Unit =
    response {
        HttpStatusCode.OK to {
            this.description = description

            if (T::class == Unit::class) return@to

            body<T> {
                this.description = bodyDescription
            }
        }
    }

/**
 * Add 404 response to the route documentation
 *
 * @param description Response description
 */
inline fun <reified T> OpenApiRoute.notFoundResponse(description: String? = null): Unit =
    response {
        HttpStatusCode.NotFound to {
            this.description = description
            body<ErrorResponse<T>>()
        }
    }

/**
 * Add 404 responses to the route documentation
 *
 * @param types Variations of 404 response
 */
fun OpenApiRoute.notFoundResponse(vararg types: KType): Unit =
    response {
        HttpStatusCode.NotFound to {
            body(anyOf(types = types))
        }
    }

/**
 * Add 400 response to the route documentation
 *
 * @param description Response description
 */
inline fun <reified T> OpenApiRoute.badRequestResponse(description: String? = null): Unit =
    response {
        HttpStatusCode.BadRequest to {
            this.description = description
            body<ErrorResponse<T>>()
        }
    }

/**
 * Add 400 responses to the route documentation
 *
 * @param types Variations of 400 response
 */
fun OpenApiRoute.badRequestResponse(vararg types: KType): Unit =
    response {
        HttpStatusCode.BadRequest to {
            body(anyOf(types = types))
        }
    }

/**
 * Add 403 response to the route documentation
 *
 * @param description Response description
 */
inline fun <reified T> OpenApiRoute.forbiddenResponse(description: String): Unit =
    response {
        HttpStatusCode.Forbidden to {
            this.description = description
            body<ErrorResponse<T>>()
        }
    }

/**
 * Add request body schema to the route documentation
 *
 * @param T Request type
 * @param description Request description
 */
inline fun <reified T> OpenApiRoute.requestBody(description: String? = null): Unit =
    request {
        body<T> {
            this.description = description
        }
    }

/**
 * Add path parameter to the route documentation
 *
 * @param T Parameter type
 * @param name Parameter name
 */
inline fun <reified T> OpenApiRoute.pathParameter(name: String): Unit =
    request {
        pathParameter<T>(name)
    }

/**
 * Add query parameter to the route documentation
 *
 * @param T Parameter type
 * @param name Parameter name
 */
inline fun <reified T> OpenApiRoute.queryParameter(name: String): Unit =
    request {
        queryParameter<T>(name)
    }

/**
 * Add multipart body request to the route documentation
 *
 * @param block [OpenApiMultipartBody] Multipart request body builder
 */
fun OpenApiRoute.multipartRequest(block: OpenApiMultipartBody.() -> Unit): Unit =
    request {
        multipartBody(block)
    }
