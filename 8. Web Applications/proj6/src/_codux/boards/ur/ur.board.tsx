import { createBoard } from '@wixc3/react-board';
import { UR } from '../../../components/ur/ur';

export default createBoard({
    name: 'UR',
    Board: () => <UR />
});
