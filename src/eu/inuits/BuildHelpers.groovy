package eu.inuits
import jenkins.model.Jenkins
import hudson.plugins.git.util.BuildData
import hudson.plugins.git.GitTagAction
import com.cloudbees.groovy.cps.NonCPS

class BuildHelpers implements Serializable {
    def script

    // Initialize this BuildHelpers
    BuildHelpers(script) {
        this.script = script
    }

    // Workaround for https://issues.jenkins-ci.org/browse/JENKINS-40622
    // This removes all the Build Data ang Git Tags actions on
    // Individual Builds menu.
    // Requires Jenkins weekly >= 2.29 or Jenkins LTS >= 2.31.1
    @NonCPS
    def removeGitActions(){
        def build = this.script.currentBuild.rawBuild
        build.removeActions(hudson.plugins.git.util.BuildData)
        build.removeActions(hudson.plugins.git.GitTagAction)
    }
}

