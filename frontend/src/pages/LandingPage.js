import { Header } from "../components/Header"
import { SubmitButton } from "../components/SubmitButton"
import { Textbox } from "../components/Textbox"
import { Box, Button, FormControl, FormErrorMessage, FormHelperText, HStack, Input, Link, Stack, Text } from '@chakra-ui/react'
import { useEffect, useState } from "react"


export const LandingPage = () => {

    const [baseUrl, setBaseUrl] = useState("")
    const [error, setError] = useState()
    const [createdUrlList, setCreatedUrlList] = useState({})

    const isError = false;  

    const handleSubmit = async (event) => {
        event.preventDefault();

        console.log("url: " + baseUrl)

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

        var urlencoded = new URLSearchParams()
        urlencoded.append("base_url", baseUrl)

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: urlencoded,
            redirect: 'follow'
        };
        
        let response = await fetch("http://ec2-54-255-49-140.ap-southeast-1.compute.amazonaws.com:8081/create", requestOptions)
        .then(json => json.json())
        .then(data => {
            console.log(data)
            setCreatedUrlList(prevState => ({...prevState, [data[0]]: data[1]}))
            console.log(createdUrlList)
        })
        .catch(error => setError(error))
    }

    useEffect(() => console.log(createdUrlList), createdUrlList)

    return (
        <form method="POST" onSubmit={handleSubmit}>
            <Stack maxWidth="60%" margin="auto" marginTop="20%" spacing={3}>
                    <Box> 
                        <Header text="URL Shortener"/>     
                        <Text fontSize='lg'>Enter a URL to generate a shortened version</Text> 
                    </Box> 
                    <FormControl isInvalid={isError}>
                        <Input id="urlShortenerTextbox" size="lg" placeholder='https://www.google.com/' errorBorderColor='crimson' variant="outline" value={baseUrl} onChange={e => setBaseUrl(e.target.value)} />
                        {/* {!isError ? (
                            <FormHelperText>
                                
                            </FormHelperText>
                        ) : (
                            <FormErrorMessage>URL provided is invalid</FormErrorMessage>
                        )} */}
                    </FormControl>
                    <Button size="lg" text="Generate URL" colorScheme="teal" onClick={handleSubmit}>Generate URL</Button>
                    <Text fontSize='sm'>*Backend is deployed to EC2 so the URL provided is longer in this case, click to redirect to original URL</Text>
                    {Object.keys(createdUrlList).length > 0 && Object.entries(createdUrlList).map(([key, value]) => {
                        return (
                            <HStack>
                                <Text key={key}>Original URL: {key}</Text>
                                <Text key={value}>Shortened URL:  
                                    <Link href={"http://ec2-54-255-49-140.ap-southeast-1.compute.amazonaws.com:8081/" + [value]} color="blue">http://ec2-54-255-49-140.ap-southeast-1.compute.amazonaws.com:8081/{value} </Link>
                                </Text>
                            </HStack>
                        )
                    })}
            </Stack>
        </form>
    )
}