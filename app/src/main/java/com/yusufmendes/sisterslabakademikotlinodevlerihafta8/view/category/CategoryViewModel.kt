package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.view.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.model.Category
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.repository.HomeRepository

class CategoryViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    var categoryLiveData: MutableLiveData<List<Category>> = MutableLiveData()

    fun getCategory(refCategory: DatabaseReference) =
        homeRepository.getCategoryFromFirebase(refCategory, categoryLiveData)
}