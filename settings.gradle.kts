rootProject.name = "comment-it-v2"

include("client")
include("server")

buildCache {
    local {
        isEnabled = !System.getenv().containsKey("CI")
    }
}