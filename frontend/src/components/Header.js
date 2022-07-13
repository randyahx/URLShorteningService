import { Heading } from '@chakra-ui/react'

export const Header = props => {
    
    return (
        <Heading as='h2' size='xl'>
            {props.text}
        </Heading>
    )
}