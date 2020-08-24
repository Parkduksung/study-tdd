package com.example.study_tdd.chapter

import android.os.Bundle
import com.example.study_tdd.BaseActivity
import com.example.study_tdd.R

class Chapter1 : BaseActivity(R.layout.activity_chapter1) {

    //1부에서는 TDD 의 리듬을 보도록 하 것.

    // 1. 작은 테스트 하나를 추가한다.
    // 2. 모든 테스트를 실행해서 테스트가 실패하는 것을 확인한다.
    // 3. 조금 수정한다.
    // 4. 모든 테스트를 실행해서 테스트가 성공하는 것을 확인한다.
    // 5. 중복을 제거하기 위해 리팩토링 한다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



}
class Dollars(private val count: Int) {
    fun times(mul: Int): Int {
        return count * mul
    }
}