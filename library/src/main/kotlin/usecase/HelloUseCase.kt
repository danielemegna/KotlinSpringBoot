package usecase

class HelloUseCase : UseCase {
    override fun run(springRequest: SpringRequest) =
        "Hello " +
            springRequest.pathVariables.getOrDefault("name", "World") +
            " from Kotlin!"
}