package com.lyl.chaoji.app

import java.io.IOException

/**
 *
 * Created by ouyuan on 2019-09-10.
 *
 */
class ApiException(val code: Int,val  info: String?) : IOException()