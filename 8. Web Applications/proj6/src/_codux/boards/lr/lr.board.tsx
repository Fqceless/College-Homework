import { createBoard } from '@wixc3/react-board';
import { LR } from '../../../components/lr/lr';

export default createBoard({
    name: 'LR',
    Board: () => <LR />
});
