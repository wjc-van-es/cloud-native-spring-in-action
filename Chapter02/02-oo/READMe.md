# Chapter 02 - Our Own Endeavour
In this chapter, you'll build a Catalog Service application. You can initialize a Spring Boot project using your
favorite method. This guide describes how to do that with the Spring Initializr application and with its REST API.
Either way, you'll get a zip archive that you can extract and import in your IDE.

## Installing tools to run a Kubernetes test cluster locally on Ubuntu laptop
* [https://kubernetes.io/docs/tasks/tools/](https://kubernetes.io/docs/tasks/tools/)
* Kind 
  * [https://kind.sigs.k8s.io/](https://kind.sigs.k8s.io/)
  * [https://kind.sigs.k8s.io/docs/user/quick-start/#installation](https://kind.sigs.k8s.io/docs/user/quick-start/#installation)
  * [https://github.com/kubernetes-sigs/kind](https://github.com/kubernetes-sigs/kind)
* We chose to install kind with Go, which we installed as well:
  * [https://www.cyberciti.biz/faq/how-to-install-gol-ang-on-ubuntu-linux/](https://www.cyberciti.biz/faq/how-to-install-gol-ang-on-ubuntu-linux/)
  * [https://www.digitalocean.com/community/tutorials/how-to-install-go-on-ubuntu-20-04](https://www.digitalocean.com/community/tutorials/how-to-install-go-on-ubuntu-20-04)
  * [https://gist.github.com/nex3/c395b2f8fd4b02068be37c961301caa7#file-path-md](https://gist.github.com/nex3/c395b2f8fd4b02068be37c961301caa7#file-path-md)
  * [https://go.dev/doc/](https://go.dev/doc/)
* Lastly we installed kubectl using snap
* [https://github.com/redhat-developer/intellij-kubernetes](https://github.com/redhat-developer/intellij-kubernetes)

### Subsequently, installing and testing go, kind and kubectl
```bash
$ sudo apt update
$ sudo apt upgrade
$ sudo apt search golang-go
$ sudo apt search gccgo-go
$ sudo apt install golang-go

# test go installation
$ go version
$ cd ~/git/cloud-native-spring-in-action/Chapter02/02-oo
$ go run hello.go
Hello, world!
willem , Let's be friends!
$ go build hello.go
$ ls -l hello*
-rwxrwxr-x 1 willem willem 1766226 mrt 18 18:03 hello
-rw-rw-r-- 1 willem willem     298 mrt 18 17:59 hello.go
$ ./hello
Hello, world!
willem , Let's be friends!

$ go install sigs.k8s.io/kind@v0.17.0
$ kind create cluster
command kind not found
# add $(go env GOPATH)/bin to the PATH environment variable
$ vim ~/.bashrc
i
INSERT

# set up Go lang path at the end of the file
# this is necessary later when you want kind to see be recognized as command
export PATH=$PATH:/usr/local/go/bin:$GOPATH/bin
:wq
$ source ~/.bashrc
$ kind create cluster
$ kind get clusters
kind
$ snap install kubectl --classic
$ kubectl version --client
WARNING: This version information is deprecated and will be replaced with the output from kubectl version --short.  Use --output=yaml|json to get the full version.
Client Version: version.Info{Major:"1", Minor:"26", GitVersion:"v1.26.2", GitCommit:"fc04e732bb3e7198d2fa44efa5457c7c6f8c0f5b", GitTreeState:"clean", BuildDate:"2023-03-01T02:22:36Z", GoVersion:"go1.19.6", Compiler:"gc", Platform:"linux/amd64"}
Kustomize Version: v4.5.7
$ kubectl version --output=yaml
clientVersion:
  buildDate: "2023-03-01T02:22:36Z"
  compiler: gc
  gitCommit: fc04e732bb3e7198d2fa44efa5457c7c6f8c0f5b
  gitTreeState: clean
  gitVersion: v1.26.2
  goVersion: go1.19.6
  major: "1"
  minor: "26"
  platform: linux/amd64
kustomizeVersion: v4.5.7
serverVersion:
  buildDate: "2022-10-25T19:35:11Z"
  compiler: gc
  gitCommit: 434bfd82814af038ad94d62ebe59b133fcb50506
  gitTreeState: clean
  gitVersion: v1.25.3
  goVersion: go1.19.2
  major: "1"
  minor: "25"
  platform: linux/amd64

$ kubectl cluster-info --context kind-kind
Kubernetes control plane is running at https://127.0.0.1:34231
CoreDNS is running at https://127.0.0.1:34231/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy

To further debug and diagnose cluster problems, use 'kubectl cluster-info dump'.

```

## Initialize Catalog Service with Spring Initializr - Website

From the [Spring Initialzr](https://start.spring.io/) website, choose the following:

* _Project_: Maven
* _Spring Boot_: 2.6.3 (or the latest production version available, 3.0.5. We chose the latter.)
* _Group_: `com.polarbookshop`
* _Artifact_: `catalog-service`
* _Name_: catalog-service
* _Package name_: `com.polarbookshop.catalogservice`
* _Java version_: 17
* _Dependencies_: Spring Web

Then, click "Generate" to download the `catalog-service.zip` archive containing the project.