package com.mercadolibre.android.example

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.mercadolibre.android.cardform.internal.CardFormWithFragment
import com.mercadolibre.android.cardform.internal.LifecycleListener

/**
 * A simple [Fragment] subclass.
 * Use the [OneTapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneTapFragment : Fragment(), LifecycleListener {

    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one_tap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.startAnim)
        button.setOnClickListener {
            activity?.apply {
                CardFormWithFragment.Builder.withAccessToken(
                    "APP_USR-7092-122619-fc2376471063df48cf0c9fcd26e00729-506902649",
                    "MLA", "test_flow")
                    .build()
                    .start(supportFragmentManager, REQUEST_CODE, R.id.container)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let {result ->
            context?.let {
                Toast.makeText(it, result.dataString, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val TAG = "ONE_TAP"
        private const val REQUEST_CODE = 213

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment OneTapFragment.
         */
        fun newInstance() = OneTapFragment()
    }
}