class Activity {

    def name
    def arguments = [logLevel: 'info']
    def parameters = [:]

    Activity(String name) {
        this.name = name
    }

    def changeLogParameters(tokenMap) {
        tokenMap.each {
            parameters[it.key] = it.value
        }
    }

    def methodMissing(String name, args) {
        arguments[name] = args[0]
    }
}
