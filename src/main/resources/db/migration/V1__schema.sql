create sequence hibernate_sequence;

create table children (
  id      integer not null constraint children_pkey primary key,
  version integer,
  name    text
);

create table parents (
  id      integer not null constraint parents_pkey primary key,
  version integer,
  name    text
);

create table parents_children (
  child_id  integer not null constraint fk_parents_children_child_id references children,
  parent_id integer not null constraint fk_parents_children_parent_id references parents,
  constraint parents_children_pkey primary key (child_id, parent_id)
);
