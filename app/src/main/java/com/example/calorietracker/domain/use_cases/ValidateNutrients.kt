package com.example.calorietracker.domain.use_cases

class ValidateNutrients {

    operator fun invoke(
        carbRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ):NutrientResult{
        val carbRatio = carbRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if(carbRatio == null || proteinRatio ==null || fatRatio == null)
            return NutrientResult.Error("None of the above fields can be empty")


        if(carbRatio + proteinRatio + fatRatio != 100)
            return NutrientResult.Error("The sum of the above field needs to be 100")

        return NutrientResult.Success(
            carbRatio = carbRatio/100f,
            proteinRatio = proteinRatio/100f,
            fatRatio = fatRatio/100f
        )

    }


}

sealed class NutrientResult{
    data class Success(
        val carbRatio:Float,
        val proteinRatio:Float,
        val fatRatio:Float
    ):NutrientResult()

    data class Error(val msg:String):NutrientResult()
}