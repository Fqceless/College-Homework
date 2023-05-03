import { createBoard } from '@wixc3/react-board';
import { Planner } from '../../../components/planner/planner';

export default createBoard({
    name: 'Planner',
    Board: () => <Planner />,
    environmentProps: {
        canvasWidth: 1088,
        windowWidth: 1276,
    },
});
