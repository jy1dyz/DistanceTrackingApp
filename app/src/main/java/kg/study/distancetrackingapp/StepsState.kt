package kg.study.distancetrackingapp

data class StepsState(
    val steps: Int = 0,
    val distance: Double = 0.0
)

sealed class StepsSideEffect
