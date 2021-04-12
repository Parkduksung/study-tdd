package com.example.study_tdd.macaddress


class AlternativeMacAddress(
    private val wideVineId: WideVineId,
    private val ssaId: SSAId,
    private val temporaryDateData: TemporaryDateData
) {

    private lateinit var convertHashCode: ConvertHashCode
    private lateinit var transformDigitAddress: TransformDigitAddress

    fun getAlternativeMacAddress(): String {
        if (!(wideVineId.getAddress() || ssaId.getAddress())) {
            return temporaryDateData.address
        } else {
            convertHashCode = ConvertHashCode(wideVineId.address + ssaId.address)

            return if (convertHashCode.getAddress()) {
                transformDigitAddress = TransformDigitAddress(convertHashCode.address)

                if(transformDigitAddress.getAddress()){
                    transformDigitAddress.address
                }else{
                    temporaryDateData.address
                }
            } else {
                temporaryDateData.address
            }
        }
    }
}