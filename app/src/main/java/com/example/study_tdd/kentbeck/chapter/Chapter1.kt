package com.example.study_tdd.kentbeck.chapter

import android.os.Bundle
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.R
import com.example.study_tdd.databinding.ActivityChapter1Binding
import com.example.study_tdd.macaddress.NetworkUtil

class Chapter1 : BaseActivity<ActivityChapter1Binding>(R.layout.activity_chapter1) {

    //1부에서는 TDD 의 리듬을 보도록 하 것.

    // 1. 작은 테스트 하나를 추가한다.
    // 2. 모든 테스트를 실행해서 테스트가 실패하는 것을 확인한다.
    // 3. 조금 수정한다.
    // 4. 모든 테스트를 실행해서 테스트가 성공하는 것을 확인한다.
    // 5. 중복을 제거하기 위해 리팩토링 한다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvMacAddress.text =  NetworkUtil.getAlternativeMacAddress()

    }


}

class Dollars(private val count: Int) : Money() {
    override fun times(mul: Int): Int {
        amount = count
        return amount * mul
    }

}

class Franc(private val count: Int) : Money() {

    override fun times(mul: Int): Int {
        amount = count
        return amount * mul
    }
}

abstract class Money {
    protected var amount: Int = 0
    abstract fun times(mul: Int): Int

}