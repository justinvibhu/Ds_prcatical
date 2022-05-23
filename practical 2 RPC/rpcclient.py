import xmlrpc.client
proxy =xmlrpc.client.ServerProxy("http://localhost:8000/")
n = int(input("Enter a number to calculate factorial:")) 
factorial = proxy.fact(n)
print("Response from server..")
print("Factorial of number is :",factorial)

