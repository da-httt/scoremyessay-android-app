package com.example.scoremyessay.ui.main.fragment.createOrder

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentCreateEssayEnterBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.github.onecode369.wysiwyg.WYSIWYG

class EnterEssayFragment : BaseFragmentWithViewBinding<FragmentCreateEssayEnterBinding>() {

    private val mActivity by lazy { activity as ActivityMain }
    private val mViewModel by lazy { mActivity.viewModel }

    override fun handleTask() {
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        mViewModel.mPostOrderRequest.observe(viewLifecycleOwner,{
            binding.editor.html = it.essay.content
        })
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
            mViewModel.mPostOrderRequest.value!!.essay.content = binding.editor.html?.trim().toString()
        }
        binding.btnComplete.setOnClickListener {
            mViewModel.mPostOrderRequest.value!!.essay.content = binding.editor.html?.trim().toString()
            controller.popBackStack()
        }

        binding.editor.setOnTextChangeListener(object : WYSIWYG.OnTextChangeListener {
            override fun onTextChange(text: String?) {
                binding.btnComplete.isEnabled = text?.trim() != ""
            }
        })
    }

    private fun initView() {
        val wysiwygEditor = binding.editor
        wysiwygEditor.setEditorHeight(200)
        wysiwygEditor.setEditorFontSize(16)
        wysiwygEditor.setPadding(10, 10, 10, 10)
        wysiwygEditor.setPlaceholder("Insert your notes here...")

//        val mPreview = preview as TextView
//        wysiwygEditor.setOnTextChangeListener(OnTextChangeListener { text -> mPreview.setText(text) })

        binding.actionUndo.setOnClickListener {
            Log.e("TAG", "onCreate: wqwewq")
            wysiwygEditor.undo()
        }

        binding.actionRedo.setOnClickListener { wysiwygEditor.redo() }

        binding.actionBold.setOnClickListener { wysiwygEditor.setBold() }

        binding.actionItalic.setOnClickListener { wysiwygEditor.setItalic() }

        binding.actionSubscript.setOnClickListener { wysiwygEditor.setSubscript() }
//
        binding.actionSuperscript.setOnClickListener {

            wysiwygEditor.setSuperscript()
        }

        binding.actionStrikethrough.setOnClickListener { wysiwygEditor.setStrikeThrough() }

        binding.actionUnderline.setOnClickListener { wysiwygEditor.setUnderline() }

        binding.actionHeading1.setOnClickListener {
            wysiwygEditor.setHeading(
                1
            )
        }

        binding.actionHeading2.setOnClickListener {
            wysiwygEditor.setHeading(
                2
            )
        }

        binding.actionHeading3.setOnClickListener {
            wysiwygEditor.setHeading(
                3
            )
        }

        binding.actionHeading4.setOnClickListener {
            wysiwygEditor.setHeading(
                4
            )
        }

        binding.actionHeading5.setOnClickListener {
            wysiwygEditor.setHeading(
                5
            )
        }

        binding.actionHeading6.setOnClickListener {
            wysiwygEditor.setHeading(
                6
            )
        }

        binding.actionTxtColor.setOnClickListener(object : View.OnClickListener {
            private var isChanged = false
            override fun onClick(v: View) {
                wysiwygEditor.setTextColor(if (isChanged) Color.BLACK else Color.RED)
                isChanged = !isChanged
            }
        })

        binding.actionIndent.setOnClickListener { wysiwygEditor.setIndent() }

        binding.actionOutdent.setOnClickListener { wysiwygEditor.setOutdent() }

        binding.actionAlignLeft.setOnClickListener { wysiwygEditor.setAlignLeft() }

        binding.actionAlignCenter.setOnClickListener { wysiwygEditor.setAlignCenter() }

        binding.actionAlignRight.setOnClickListener { wysiwygEditor.setAlignRight() }

        binding.actionAlignJustify.setOnClickListener { wysiwygEditor.setAlignJustifyFull() }

        binding.actionBlockquote.setOnClickListener { wysiwygEditor.setBlockquote() }

        binding.actionInsertBullets.setOnClickListener { wysiwygEditor.setBullets() }

        binding.actionInsertNumbers.setOnClickListener { wysiwygEditor.setNumbers() }

        binding.actionInsertImage.setOnClickListener {
            wysiwygEditor.insertImage(
                "https://i.postimg.cc/JzL891Fm/maxresdefault.jpg",
                "Night Sky"
            )
        }

//        binding.actionInsertLink.setOnClickListener{
//            wysiwygEditor.insertLink(
//                "https://github.com/onecode369",
//                "One Code"
//            )
//        }

        binding.actionInsertCheckbox.setOnClickListener { wysiwygEditor.insertTodo() }

        var visible = false

        binding.preview.setOnClickListener {
            if (!visible) {
                wysiwygEditor.setInputEnabled(false)
                binding.preview.setImageResource(R.drawable.visibility_off)
            } else {
                wysiwygEditor.setInputEnabled(true)
                binding.preview.setImageResource(R.drawable.visibility)
            }
            visible = !visible
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentCreateEssayEnterBinding =
        FragmentCreateEssayEnterBinding.inflate(inflater, container, false)
}