package ali.doran.common_library

import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList

class ListTools {

    fun <T> listSearch(
        inputModelList: List<T>,
        filterFieldList: List<String>,
        searchString: String
    ): List<T> {
        val outputList = ArrayList<T>()
        for (inputModel in inputModelList) {
            for (filterField in filterFieldList) {
                val fieldList = inputModel!!::class.java.declaredFields.toList()
                for (field: Field in fieldList)
                    if (field.name.equals(filterField)) {
                        field.isAccessible = true
                        val o = field.get(inputModel)
                        val fieldValue = Objects.requireNonNull(o).toString()
                        val separatedFieldValueList = fieldValue.split(" ".toRegex()).toTypedArray()
                        for (separatedFieldValue in separatedFieldValueList)
                            if (separatedFieldValue.lowercase()
                                    .contains(searchString.lowercase())
                            ) {
                                outputList.add(inputModel)
                                break
                            }
                    }
            }
        }
        return outputList
    }
}