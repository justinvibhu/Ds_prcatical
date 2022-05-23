from xmlrpc.server import SimpleXMLRPCServer 
import xmlrpc.client
def fact(n):
	if n < 0:
		return 0
	elif n == 0 or n == 1:
		return 1
	else:
		fact = 1
	while(n > 1):
		fact *= n
		n -= 1
	return fact
server =SimpleXMLRPCServer(("localhost",8000))
print("Server i litening on port 8000...")
server.register_function(fact,"fact")
server.serve_forever()
