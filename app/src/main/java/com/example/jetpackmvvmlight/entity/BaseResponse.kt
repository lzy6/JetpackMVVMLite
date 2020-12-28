package com.example.jetpackmvvmlight.entity

import com.example.jetpackmvvmlight.app.Constant
import java.io.Serializable

/**
 * ================================================
 * 如果你服务器返回的数据格式固定为这种方式(这里只提供思想,服务器返回的数据格式可能不一致,可根据自家服务器返回的格式作更改)
 * 指定范型即可改变 `data` 字段的类型, 达到重用 [BaseResponse], 如果你实在看不懂, 请忽略
 *
 *
 * Created by JessYan on 26/09/2016 15:19
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [FollowSchool me](https://github.com/JessYanCoding)
 * ================================================
 */
class BaseResponse<T> : Serializable {
    val data: T? = null
    val status: Int = 0
    val msg: String? = ""

    fun isSuccess(): Boolean = status == Constant.REQUEST_SUCCESS

}
