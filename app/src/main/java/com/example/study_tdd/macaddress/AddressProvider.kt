package com.example.study_tdd.macaddress


class AddressProvider(private val ssaId: SSAId, private val wideVineId: WideVineId) {

    fun create(): Address {
        return if (ssaId.getAddress().isEmpty() && wideVineId.getAddress().isEmpty()) {
            TemporaryDateData()
        } else {
            TransformDigitAddress(ConvertHashCode(ssaId.getAddress() + wideVineId.getAddress()).getAddress())
        }
    }

}

