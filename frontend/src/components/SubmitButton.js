import { Button } from '@chakra-ui/react';

export const SubmitButton = props => {
    
    return (
        <Button colorScheme={props.color} size={props.size}>
            {props.text}
        </Button>
    )
}