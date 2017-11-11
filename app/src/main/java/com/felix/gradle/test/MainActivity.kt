package com.felix.gradle.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felix.testlibrary1.Test1
import com.felix.testlibrary2.Test2
import com.felix.testlibrary3.Test3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Test1.doTest1()
        Test2.doTest2()
        Test3.doTest3()
    }
}
