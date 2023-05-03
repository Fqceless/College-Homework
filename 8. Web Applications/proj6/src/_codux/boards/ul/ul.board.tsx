import { createBoard } from '@wixc3/react-board';
import { UL } from '../../../components/ul/ul';

export default createBoard({
    name: 'UL',
    Board: () => <UL />,
    environmentProps: {
        canvasWidth: 503,
        canvasHeight: 347,
    },
});
