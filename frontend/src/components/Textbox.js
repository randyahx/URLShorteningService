import { Input } from '@chakra-ui/react'

export const Textbox = props => {

    return (
        <Input
            id={props.id}
            variant={props.variant} 
            placeholder='https://www.google.com/' 
            size={props.size} 
            errorBorderColor='crimson' 
            />
    )
}
