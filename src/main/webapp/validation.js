function loginValidation()
{
	const email=document.getElementById("email").value;
	const password=document.getElementId("password").value;
	
		if(email==="" || !email.includes("@"))
		{
			alert("Invalid email!!");
		}
		if(password==="")
		{
			alert("Invalid password!!")	
		}
}