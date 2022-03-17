package com.example.settinglib.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlib.app.Constant
import com.example.commonlib.app.RoutePath
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.mmkv
import com.example.commonlib.viewBinding
import com.example.settinglib.R
import com.example.settinglib.databinding.ActivitySettingBinding
import com.example.settinglib.ui.viewmodel.SettingViewModel


@Route(path = RoutePath.SettingActivity)
class SettingActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivitySettingBinding::inflate)
    private val viewModel by viewModels<SettingViewModel>()
    private val fragmentTransaction by lazy {
        supportFragmentManager.beginTransaction()
    }

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    private fun initClick() {
        viewBind.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        fragmentTransaction.add(R.id.settings, SettingFragment()).commit()
    }

    class SettingFragment : PreferenceFragmentCompat() {

        private lateinit var switchPreNight: SwitchPreference
        private val SWITCH_PRE_NIGHT_KEY = "switch_pre_night_key"

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preference_settings)
            switchPreNight = findPreference(SWITCH_PRE_NIGHT_KEY)!!
            val mode = mmkv().getInt(Constant.NIGHT_MODE, Constant.NO_NIGHT_MODE)
            switchPreNight.isChecked = mode == Constant.IS_NIGHT_MODE
            switchPreNight.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val value = newValue as Boolean
                    if (value){
                        mmkv().putInt(Constant.NIGHT_MODE,Constant.IS_NIGHT_MODE)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }else{
                        mmkv().putInt(Constant.NIGHT_MODE,Constant.NO_NIGHT_MODE)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    recreate(requireActivity())
                    true
                }
        }
    }
}