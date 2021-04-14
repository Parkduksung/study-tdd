package com.example.study_tdd.macaddress

import java.lang.StringBuilder
import kotlin.math.abs

class ConvertHashCode(private val notConvertAddress: String) : Address {
    override fun getAddress(): String {
        return abs(notConvertAddress.hashCode()).toString()
    }
}

class TransformDigitAddress(private val notTransformAddress: String) : Address {

    override fun getAddress(): String {

        val transformAddressBuilder =
            StringBuilder(notTransformAddress)

        val addArray = arrayOf("4", "4", "3", "0")

        // 12자리 숫자를 만들어 주기 위한 로직.

        return if (transformAddressBuilder.length < 12) {
            val convertHashCodeLength = transformAddressBuilder.length
            // 12자리 숫자가 아닌경우 빈공간에 순차적으로 배열의 값을 추가하여 12자리를 만들어준다.
            for (i in convertHashCodeLength..11) {
                transformAddressBuilder.append(addArray[(11 - i) % 4])
            }
            transformAddressBuilder.toString()
        } else {
            // 12자리 숫자 초과인 경우 12자리까지 자른다.
            transformAddressBuilder.substring(0, 12)
        }
    }
}

