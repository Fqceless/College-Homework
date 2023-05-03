import styles from './ll.module.scss';
import classNames from 'classnames';

export interface LLProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-lls-and-templates
 */
export const LL = ({ className }: LLProps) => {
    return (
        <div className={classNames(styles.root, className)}>
            
        </div>
    );
};
