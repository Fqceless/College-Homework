import styles from './planner.module.scss';
import classNames from 'classnames';
import { UL } from '../ul/ul';
import { Header } from '../header/header';
import { UR } from '../ur/ur';
import { LL } from '../ll/ll';
import { LR } from '../lr/lr';

export interface PlannerProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-planners-and-templates
 */
export const Planner = ({ className }: PlannerProps) => {
    return (
        <div className={classNames(styles.root, className)}>
            <div className={styles.Organizer}>
                <Header />
                <div className={styles.MainBody}>
                    <UL className={styles.UL} />
                    <UR className={styles.UR} />
                    <LL />
                    <LR />
                </div>
            </div>
        </div>
    );
};
