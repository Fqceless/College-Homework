import { createBoard } from '@wixc3/react-board';
import { LL } from '../../../components/ll/ll';

export default createBoard({
    name: 'LL',
    Board: () => <LL />
});
