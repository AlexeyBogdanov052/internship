class Activity {

    def name
    def arguments = [logLevel: 'info']
    def parameters = [:]

    Activity(String name) {
        this.name = name
    }
}
