// Hello Word in Go by Vivek Gite
package main

// Import OS and fmt packages
import (
	"fmt"
	"os"
)

// Let us start
func main() {
    fmt.Println("Hello, world!")  // Print simple text on screen
    fmt.Println(os.Getenv("USER"), ", thank you for running me on your computer.") // Read Linux $USER environment variable
}